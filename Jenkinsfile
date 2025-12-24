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

    post {
        success {
            echo "Pipeline terminé avec succès ✅"
        }
        failure {
            echo "Pipeline échoué ❌"
        }
    }
}
