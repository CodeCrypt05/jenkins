pipeline {
    agent any

    stages {
        stage('git pull') {
            steps {
                // Cloning the repository from GitHub
                git url: 'https://github.com/CodeCrypt05/Jenkins-Distribution',
                branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                // Running Flutter tests
                bat 'flutter test'
            }
        }

        stage('Build Android APK') {
            steps {
                // Building the APK in release mode
                bat 'flutter build apk'
            }
        }
        
        stage('Archive APK') {
            steps {
                // Archiving the release APK
                archiveArtifacts artifacts: '**/build/app/outputs/flutter-apk/app-release.apk', allowEmptyArchive: false
            }
        }
    }
       
    post {
        always {
            echo 'Pipeline finished.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}