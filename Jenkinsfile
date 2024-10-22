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
                echo "Checking out the ${params.BRANCH_NAME} branch..."
                checkout([$class: 'GitSCM', branches: [[name: "${params.BRANCH_NAME}"]], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
            }
        }

        stage('Inject Secrets') {
            steps {
                script {
                    // Define the destination path for secrets.yml
                    def secretsFilePath = 'profile-service\\src\\main\\resources\\secrets.yml'
                    
                    // Check if the branch is release/2024_M10 and adjust the source path accordingly
                    if (params.BRANCH_NAME == 'release/2024_M10') {
                        echo 'Injecting secrets.yml for release/2024_M10...'
                        bat "copy src\\release\\2024_M10\\resources\\secrets.yml ${secretsFilePath}"
                    } else {
                        echo 'Injecting secrets.yml for main...'
                        withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                            bat 'copy %SECRET_FILE% src\\main\\resources\\secrets.yml'
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo "Building the project for branch: ${params.BRANCH_NAME}..."
                dir('profile-service') {
                    bat 'mvn clean package'
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
}
