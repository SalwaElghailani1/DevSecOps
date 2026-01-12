pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SonarCloud-Token')
    }

    stages {
        stage('SCM Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    echo "=== Java Version ==="
                    java -version
                    echo "=== Maven Version ==="
                    mvn -version
                    echo "=== Build & Test ==="
                    mvn clean verify
                '''
            }
        }

        stage('Dependency-Check') {
            steps {
                sh '''
                    echo "=== OWASP Dependency-Check ==="
                    mvn org.owasp:dependency-check-maven:check
                '''
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/dependency-check-report.html', allowEmptyArchive: true
                }
            }
        }
        stage('Trivy Security Scan') {
            steps {
                sh '''
                docker run --rm \
                  -v /var/run/docker.sock:/var/run/docker.sock \
                  -v $WORKSPACE/reports:/reports \
                  aquasec/trivy image \
                  --severity HIGH,CRITICAL \
                  --format json \
                  --output /reports/trivy-report.json \
                  myusername/myimage:latest
                '''
            }
            post {
                always {
                    archiveArtifacts artifacts: 'reports/trivy-report.json', allowEmptyArchive: true
                    echo "Trivy scan finished. Report saved in reports/trivy-report.json"
                }
            }
        }
        stage('SonarCloud Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=salwa_devsecops-jenkins \
                          -Dsonar.organization=salwa \
                          -Dsonar.host.url=https://sonarcloud.io \
                          -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
     stage('Dependency-Check') {
            steps {
                dir('maven') { 
                    // Run Maven commands 
                        sh 'mvn org.owasp:dependency-check-maven:check'
                    }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/dependency-check-report.html', allowEmptyArchive: true
                }
            }
        }

    post {
        success {
            echo "Pipeline terminé avec succès ✅"
        }
        failure {
            echo "Pipeline échoué ❌"
        }
    }
   

}

