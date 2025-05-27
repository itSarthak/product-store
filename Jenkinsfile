pipeline {
  agent any
  environment {
        RENDER_API_KEY = credentials('render-api-key')
        // Replace with the backend deploy hook you copied
        RENDER_BACKEND_DEPLOY_HOOK = credentials('render-backend-deploy-hook')
        // Replace with the frontend deploy hook you copied
        RENDER_FRONTEND_DEPLOY_HOOK = "https://api.render.com/deploy/srv-cv2udl2j1k6c739pp0lg?key=your-api-key"
    }
  options {
    skipDefaultCheckout()
  }
  tools {
    maven "mvn"
    nodejs "node"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', credentialsId: 'Git token', url: 'https://github.com/itSarthak/product-store.git'
      }
    }

    stage('Build') {
      paralled {
        stage('Java') {
          steps {
            dir('backend') {
              sh 'mvn clean install'
            }
          }
        }

        stage('React') {
          steps {
            dir('frontend') {
              sh 'npm install'
              sh 'npm run build'
            }
          }
        }
      }
    }
    stage('Sonar') {
      steps {
        dir('backend') {
          withSonarQubeEnv('MySonarServer') {
              sh 'mvn sonar:sonar'
          }
        }
      }
    }
    stage('Test') {
      steps {
        srcript {
          sh 'cd backend && mvn test'
        }
      }
    }
    stage('Deploy to Render') {
            steps {
                script {
                    echo "Deploying Backend..."
                    def backendResponse = httpRequest(
                        url: "${RENDER_BACKEND_DEPLOY_HOOK}",
                        httpMode: 'POST',
                        validResponseCodes: '200:299'
                    )
                    echo "Render Backend Deployment Response: ${backendResponse}"
        
                    // echo "Deploying Frontend..."
                    // def frontendResponse = httpRequest(
                    //     url: "${RENDER_FRONTEND_DEPLOY_HOOK}",
                    //     httpMode: 'POST',
                    //     validResponseCodes: '200:299'
                    // )
                    // echo "Render Frontend Deployment Response: ${frontendResponse}"
                }
            }
        }
  }
  post {
    success {
      // Actions after the build succeeds
      echo 'Build was successful!'
    }
    failure {
      // Actions after the build fails
      echo 'Build failed. Check logs.'
    }
  }
}