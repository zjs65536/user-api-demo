pipeline {
  agent {
    node {
      label 'node2'
    }
  }

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
        sh 'docker build -t ivanyou98/webapp:${BUILD_NUMBER} .'
      }
    }

    stage('Publish to Docker Hub') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
          sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
          sh 'docker push ivanyou98/webapp:${BUILD_NUMBER}'
        }
      }
    }

    stage('Run Docker Container') {
      steps {
        sh 'docker pull '
        sh 'docker run -d -p 8086:8080 ivanyou98/webapp:${BUILD_NUMBER}'
      }
    }
  }
}
