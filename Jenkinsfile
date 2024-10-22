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
                        echo 'Stage of Build: main branch...'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Stage of Build: release/2024_M10 branch...'
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
                        echo 'Stage of Test: main branch...'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Stage of Build: release/2024_M10 branch...'
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
                        echo 'Stage of Deploy: main branch...'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Stage of Deploy: release/2024_M10 branch...'
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
