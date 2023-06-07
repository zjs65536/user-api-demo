pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/IvanYou98/user-api-demo'
      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t webapp:${BUILD_NUMBER} .'
      }
    }

    stage('Publish to Docker Hub') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
          sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
          sh 'docker push your-image-name'
        }
      }
    }

    stage('Run Docker Container') {
      steps {
        sh 'docker run -d -p 8086:8080 your-image-name'
      }
    }
  }
}
