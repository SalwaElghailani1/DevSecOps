node {
    stage('SCM') {
        checkout scm
    }
    
    stage('Build & Test') {
        sh '''
          java -version
          mvn -version
          mvn clean verify
        '''
    }

    stage('SonarCloud Analysis') {
        withSonarQubeEnv('SonarCloud') { 
            sh '''
              echo "=== Starting SonarCloud Analysis ==="
              mvn sonar:sonar \
                -Dsonar.projectKey=salwa_devsecops-jenkins \
                -Dsonar.organization=salwa \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.login=$SONAR_TOKEN
            '''
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

    stage('Quality Gate') {
        timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
        }
    }
}
