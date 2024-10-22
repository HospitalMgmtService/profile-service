pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                script {
                    echo 'Stage of Checkout Git Repo'

                    if (env.BRANCH_NAME == 'main') {
                        echo 'Checking out main branch...'
                        // Checkout the main branch
                        checkout([$class: 'GitSCM', 
                                  branches: [[name: '*/main']], 
                                  userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], 
                                  extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]
                        ])
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Checking out release/2024_M10 branch...'
                        // Checkout the release/2024_M10 branch
                        checkout([$class: 'GitSCM', 
                                  branches: [[name: '*/release/2024_M10']], 
                                  userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], 
                                  extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]
                        ])
                    } else {
                        echo 'Branch not recognized for checkout.'
                    }
                }
            }
        }

        stage('Inject Secrets') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo 'Stage of Inject Secrets - main branch (No secrets required)'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Stage of Inject Secrets - release/2024_M10 branch'
                            bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                        }
                    } else {
                        echo 'Branch not recognized for secrets injection.'
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Stage of Build: Building the project...'
                    bat 'dir' // List files for debugging

                    if (env.BRANCH_NAME == 'main') {
                        echo 'Building main version...'
                        bat 'mvn clean package' // Run build for main branch
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Building release/2024_M10 version...'
                        bat 'mvn clean package -DskipTests' // Skip tests for release build
                    } else {
                        echo 'Branch not recognized for building.'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Stage of Test: Running tests...'
                    bat 'dir' // List files for debugging

                    if (env.BRANCH_NAME == 'main') {
                        echo 'Running tests for main branch...'
                        bat 'mvn test' // Run tests for main branch
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Skipping tests for release/2024_M10 branch...'
                    } else {
                        echo 'Branch not recognized for testing.'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo 'Deploying to production environment for main branch...'
                        // Add your deployment logic for main branch here
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Deploying the application for release/2024_M10 branch...'
                        // Add your deployment logic for release/2024_M10 branch here
                    } else {
                        echo 'Branch not recognized for deployment.'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs() // Clean up the workspace after build
        }
        success {
            echo 'Build completed successfully.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
