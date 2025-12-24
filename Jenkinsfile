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

    stage('Quality Gate') {
        timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
        }
    }
}
