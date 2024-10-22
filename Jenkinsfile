pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build (e.g., main or release/2024_M10)')
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                script {
                    if (params.BRANCH_NAME == 'main') {
                        echo 'Checking out the main branch...'
                        checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
                    } else if (params.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Checking out the release/2024_M10 branch...'
                        checkout([$class: 'GitSCM', branches: [[name: 'release/2024_M10']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
                    } else {
                        error("Unsupported branch: ${params.BRANCH_NAME}")
                    }
                }
            }
        }

        stage('Inject Secrets') {
            steps {
                script {
                    if (params.BRANCH_NAME == 'main' || params.BRANCH_NAME == 'release/2024_M10') {
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Injecting secrets.yml'
                            bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    if (params.BRANCH_NAME == 'main') {
                        echo 'Building the main branch...'
                        bat 'mvn clean package'
                    } else if (params.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Building the release/2024_M10 branch...'
                        bat 'mvn clean package'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (params.BRANCH_NAME == 'main') {
                        echo 'Running tests for the main branch...'
                        bat 'mvn test'
                    } else if (params.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Running tests for the release/2024_M10 branch...'
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (params.BRANCH_NAME == 'main') {
                        echo 'Deploying the main branch...'
                        // Add deployment steps for the main branch
                    } else if (params.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Deploying the release/2024_M10 branch...'
                        // Add deployment steps for the release branch
                    }
                }
            }
        }
    }
}
