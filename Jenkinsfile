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

                // Clean workspace before checkout
                cleanWs()

                script {
                    // Verify if the branch exists
                    def branches = bat(script: 'git ls-remote --heads https://github.com/HospitalMgmtService/profile-service', returnStdout: true).trim()
                    if (!branches.contains(params.BRANCH_NAME)) {
                        error "Branch '${params.BRANCH_NAME}' does not exist in the repository."
                    }
                }

                checkout([$class: 'GitSCM', 
                    branches: [[name: "${params.BRANCH_NAME}"]],
                    userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']],
                    extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]
                ])
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
                    } else {
                        echo 'Injecting secrets.yml for main...'
                    }

                    withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                        bat "copy %SECRET_FILE% ${secretsFilePath}"
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo "Building the project for branch: ${params.BRANCH_NAME}..."
                dir('profile-service') {
                    bat 'dir'
                    bat 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for branch: ${params.BRANCH_NAME}..."
                dir('profile-service') {
                    bat 'dir'
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
