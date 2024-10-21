pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                echo 'Stage of Checkout Git Repo'
                // Explicitly checking out the Jenkinsfile from a specific branch
                checkout([$class: 'GitSCM', 
                          branches: [[name: "*/${env.BRANCH_NAME}"]], // Check out the current branch
                          userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], 
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]
                ])
            }
        }
        
        stage('Inject Secrets') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'release/2024_M10') {
                        // Copy secrets.yml for specific branch
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            echo 'Injecting secrets.yml for release/2024_M10'
                            bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                        }
                    } else if (env.BRANCH_NAME == 'dev') {
                        echo 'No secrets to inject for dev branch.'
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

                    if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Building release version...'
                        bat 'mvn clean package -DskipTests' // Skip tests for release builds
                    } else if (env.BRANCH_NAME == 'dev') {
                        echo 'Building development version...'
                        bat 'mvn clean package' // Run all tests for dev builds
                    } else {
                        echo 'Building other version...'
                        bat 'mvn clean package -PsomeProfile' // Example for other branches
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Stage of Test: Running tests...'
                    bat 'dir' // List files for debugging

                    if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Skipping tests for release branch...'
                    } else {
                        bat 'mvn test' // Run tests for other branches
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Deploying the application for release branch...'
                        // Add your deployment logic for release branch here
                    } else if (env.BRANCH_NAME == 'dev') {
                        echo 'Deploying to development environment...'
                        // Add your deployment logic for dev branch here
                    } else {
                        echo 'Deploying to staging environment for other branches...'
                        // Add your deployment logic for other branches here
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
