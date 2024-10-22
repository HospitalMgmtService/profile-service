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
                echo 'Injecting secrets.yml'
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'dir'
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Stage of Build: Building the project...'
                bat 'mvn clean package'
            }
        }

        stage('UAT') {
            steps {
                echo 'Stage of UAT: Injecting UAT Secrets and Building...'
                withCredentials([file(credentialsId: 'UAT_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'mvn clean package -Puat'
                }
            }
        }

        stage('Dev') {
            steps {
                echo 'Stage of Dev: Injecting Dev Secrets and Building...'
                withCredentials([file(credentialsId: 'DEV_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'mvn clean package -Pdev'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Stage of Test: Injecting Test Secrets and Building...'
                withCredentials([file(credentialsId: 'TEST_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'mvn clean package -Ptest'
                }
            }
        }

        stage('Production') {
            steps {
                echo 'Stage of Production: Injecting Production Secrets and Deploying...'
                withCredentials([file(credentialsId: 'PROD_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                    bat 'mvn clean package -Pprod'
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            bat 'del src\\main\\resources\\secrets.yml'
        }
    }
}
