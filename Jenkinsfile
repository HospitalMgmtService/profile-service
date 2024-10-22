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
                    echo "Checking out the ${params.BRANCH_NAME} branch..."
                    // Clean workspace before checkout
                    cleanWs()
                    
                    // Using Git credentials for authentication
                    checkout([$class: 'GitSCM',
                        branches: [[name: "*/${params.BRANCH_NAME}"]],  // Added * for proper branch reference
                        userRemoteConfigs: [[
                            url: 'https://github.com/HospitalMgmtService/profile-service',
                            credentialsId: 'YOUR_GIT_CREDENTIALS_ID'  // Add your Git credentials ID here
                        ]],
                        extensions: [
                            [$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service'],
                            [$class: 'CleanBeforeCheckout']  // Clean before checkout
                        ]
                    ])
                }
            }
        }

        stage('Inject Secrets') {
            steps {
                script {
                    dir('profile-service') {  // Change to profile-service directory
                        // Create resources directory if it doesn't exist
                        bat 'mkdir src\\main\\resources 2>nul || exit 0'
                        
                        if (params.BRANCH_NAME == 'release/2024_M10') {
                            echo 'Injecting secrets.yml for release/2024_M10...'
                            withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS_RELEASE', variable: 'SECRET_FILE')]) {
                                bat 'copy "%SECRET_FILE%" src\\main\\resources\\secrets.yml'
                            }
                        } else {
                            echo 'Injecting secrets.yml for main...'
                            withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                                bat 'copy "%SECRET_FILE%" src\\main\\resources\\secrets.yml'
                            }
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo "Building the project for branch: ${params.BRANCH_NAME}..."
                dir('profile-service') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for branch: ${params.BRANCH_NAME}..."
                dir('profile-service') {
                    bat 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying the project for branch: ${params.BRANCH_NAME}..."
                // Add deployment logic here if necessary
            }
        }
    }

    post {
        always {
            cleanWs()  // Clean workspace after build
        }
    }
}
