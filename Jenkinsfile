pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                echo 'Stage of Checkout Git Repo'
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
            }
        }
        
        stage('Inject Secrets') {
            steps {
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Stage of Build: Building the project...'
                bat 'dir'
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Stage of Test: Running tests...'
                bat 'dir'
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Stage of Deploy: Deploying the application...'
            }
        }
    }
}
