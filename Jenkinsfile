pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repos') {
            parallel {
                stage('Checkout Main Branch') {
                    steps {
                        echo 'Checking out the main branch...'
                        checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service-main']]])
                    }
                }

                stage('Checkout Release Branch (release/2024_M10)') {
                    steps {
                        echo 'Checking out the release/2024_M10 branch...'
                        checkout([$class: 'GitSCM', branches: [[name: 'release/2024_M10']], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service-release']]])
                    }
                }
            }
        }

        stage('Inject Secrets') {
            parallel {
                stage('Inject Secrets for Main Branch') {
                    steps {
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Injecting secrets.yml for main branch'
                            bat 'copy %SECRET_FILE% profile-service-main\\src\\main\\resources\\secrets.yml'
                        }
                    }
                }

                stage('Inject Secrets for Release Branch') {
                    steps {
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Injecting secrets.yml for release/2024_M10 branch'
                            bat 'copy %SECRET_FILE% profile-service-release\\src\\main\\resources\\secrets.yml'
                        }
                    }
                }
            }
        }

        stage('Build') {
            parallel {
                stage('Build Main Branch') {
                    steps {
                        echo 'Building main branch...'
                        dir('profile-service-main') {
                            bat 'mvn clean package'
                        }
                    }
                }

                stage('Build Release Branch') {
                    steps {
                        echo 'Building release/2024_M10 branch...'
                        dir('profile-service-release') {
                            bat 'mvn clean package'
                        }
                    }
                }
            }
        }

        stage('Test') {
            parallel {
                stage('Test Main Branch') {
                    steps {
                        echo 'Testing main branch...'
                        dir('profile-service-main') {
                            bat 'mvn test'
                        }
                    }
                }

                stage('Test Release Branch') {
                    steps {
                        echo 'Testing release/2024_M10 branch...'
                        dir('profile-service-release') {
                            bat 'mvn test'
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            parallel {
                stage('Deploy Main Branch') {
                    steps {
                        echo 'Deploying main branch...'
                        // Add your deployment logic here for main branch
                    }
                }

                stage('Deploy Release Branch') {
                    steps {
                        echo 'Deploying release/2024_M10 branch...'
                        // Add your deployment logic here for release/2024_M10 branch
                    }
                }
            }
        }
    }
}
