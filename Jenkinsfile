pipeline {
    agent any

    tools {
        maven 'maven3.8.8'
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'release/2024_M10', description: 'Branch to build (e.g., release/2024_M10)')
    }

    stages {
        stage('Checkout Git Repo') {
            steps {
                echo "Stage of Checkout Git Repo: Checking out branch ${params.BRANCH_NAME}"
                // Dynamically checking out the specified branch
                checkout([$class: 'GitSCM', branches: [[name: "${params.BRANCH_NAME}"]], userRemoteConfigs: [[url: 'https://github.com/HospitalMgmtService/profile-service']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'profile-service']]])
            }
        }
        
        stage('Inject Secrets') {
            steps {
                // Copy secrets.yml from Jenkins credentials to the working directory
                withCredentials([file(credentialsId: 'PROFILE_SERVICE_SECRETS', variable: 'SECRET_FILE')]) {
                    echo 'Injecting secrets.yml'
                    bat 'copy %SECRET_FILE% src\\release/2024_M10\\resources\\secrets.yml'
                }
            }
        }

        stage('Build') {
            steps {
                // Add your build steps here (e.g., for Maven/Gradle)
                echo 'Stage of Build: Building the project...'
                bat 'dir'
                // build the project and create a JAR file
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Add your test steps here
                echo 'Stage of Test: Running tests...'
                bat 'dir'
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Add your deployment steps here
                echo 'Stage of Deploy: Deploying the application...'
            }
        }
    }
}
