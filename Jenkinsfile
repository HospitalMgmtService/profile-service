pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                echo 'Stage of Checkout Git Repo'
                // Check out the current branch
                checkout([$class: 'GitSCM', 
                          branches: [[name: "*/${env.BRANCH_NAME}"]], 
                          userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], 
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]
                ])
            }
        }
        
        stage('Inject Secrets') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo 'Stage of Inject Secrets - branch of main'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        // Copy secrets.yml for the release branch
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Stage of Inject Secrets - branch of release/2024_M10'
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
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Building release/2024_M10 version...'
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
                        echo 'Testing main version...'
                    } else if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Testing release/2024_M10 branch...'
                    } else {
                        echo 'Testing other branch'
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
