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

    stage('Clear Sonar Cache') {
        sh '''
            echo "=== Cleaning SonarQube Cache ==="
            CACHE_DIR="/var/jenkins_home/.sonar/cache"
            if [ -d "$CACHE_DIR" ]; then
                echo "Removing cache files..."
                rm -rf "$CACHE_DIR"/*
                echo "Cache cleared successfully."
                echo "Current cache size:"
                du -sh "$CACHE_DIR" || true
            else
                echo "Cache directory not found: $CACHE_DIR"
                mkdir -p "$CACHE_DIR"
                echo "Created cache directory."
            fi
        '''
    }

    stage('SonarQube Analysis') {
        withSonarQubeEnv('SonarQube') {
            sh '''
              echo "=== Starting SonarQube Analysis ==="
              echo "Sonar URL: $SONAR_HOST_URL"
              echo "Project: DevSecOps_Jenkins"

              mvn sonar:sonar \
                -Dsonar.projectKey=DevSecOps_Jenkins \
                -Dsonar.projectName="DevSecOps_Jenkins" \
                -Dsonar.host.url=$SONAR_HOST_URL \
                -Dsonar.login=$SONAR_TOKEN \
                -Dsonar.scm.disabled=true
            '''
        }
    }
}