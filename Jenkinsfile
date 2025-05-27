pipeline {
  agent any
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