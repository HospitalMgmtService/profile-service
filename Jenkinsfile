pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                echo 'Stage of Checkout Git Repo'
                // Explicitly checking out the Jenkinsfile from a subdirectory
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service.git']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
            }
        }

        stage('Build') {
            steps {
                // Add your build steps here (e.g., for Maven/Gradle)
                echo 'Stage of Build: Building the project...'
                bat 'dir'
                // build the project and create a JAR file
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Add your test steps here
                echo 'Stage of Test: Running tests...'
                bat 'dir'
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Add your deployment steps here
                echo 'Stage of Deploy: Deploying the application...'
            }
        }
    }
}