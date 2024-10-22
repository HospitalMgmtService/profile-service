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
                bat 'dir'
            }
        }

        stage('Inject Secrets') {
            steps {
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'dir'
                }
            }
        }
        
        stage('DEV') {
            steps {
                echo 'Stage of DEV...replace PROFILE_SERVICE_SECRETS'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
                bat 'dir'
                bat 'mvn clean compile'
            }
        }
        
        stage('TEST') {
            steps {
                echo 'Stage of Test: Running tests...'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
                bat 'dir'
                bat 'mvn test'
            }
        }        

        stage('BUILD') {
            steps {
                echo 'Stage of Build: Building the project...'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
                bat 'dir'
                bat 'mvn clean package'
            }
        }
        
        stage('UAT') {
            steps {
                echo 'Stage of UAT...replace PROFILE_SERVICE_SECRETS'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
                bat 'dir'
                bat 'mvn clean package'
            }
        }           

        stage('DEPLOY') {
            steps {
                echo 'Stage of Deploy: Deploying the application...'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                }
                bat 'dir'
                // bat 'mvn clean deploy'
            }
        }
    }
}
