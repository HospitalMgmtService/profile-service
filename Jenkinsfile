pipeline {
    agent any

    stages {
        stage('Checkout Git Repo') {
            steps {
                // Checkout the git repository without credentials
                echo 'Stage of Checkout Git Repo'
                git url: 'https://github.com/HospitalMgmtService/profile-service.git', branch: 'main'
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
