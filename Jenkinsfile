node {
    stage('SCM') {
        checkout scm
    }
    
    stage('Build & Test') {
        // Optional: just to check Java/Maven versions
        sh '''
          java -version
          mvn -version
          mvn clean verify
        '''
    }

    stage('SonarQube Analysis') {
        withSonarQubeEnv('SonarQube') {
            sh '''
              mvn sonar:sonar \
              -Dsonar.projectKey=DevSecOps_Jenkins \
              -Dsonar.projectName="DevSecOps_Jenkins" \
              -Dsonar.host.url=$SONAR_HOST_URL \
              -Dsonar.login=$SONAR_TOKEN
            '''
        }
    }
}
