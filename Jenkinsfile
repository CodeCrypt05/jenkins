pipeline {
    agent any

    environment {
        GOOGLE_APPLICATION_CREDENTIALS = 'D:\\vaibhav\\jenkins\\jenkins-distribution-service-credential.json'
    } 

    stages {
        stage('git pull') {
            steps {
                // Cloning the repository from GitHub
                git url: 'https://github.com/CodeCrypt05/jenkins',
                branch: 'main'
            }
        }

        // stage('Run Tests') {
        //     steps {
        //         // Running Flutter tests
        //         bat 'flutter test'
        //     }
        // }

        stage('Build Android APK') {
            steps {
                // Building the APK in release mode
                bat 'flutter build apk --release --android-skip-build-dependency-validation'
            }
        }

        stage ('Distribute') {
            steps {
                // Running the Gradle commands with the environment variable set
                bat 'cd android && gradlew.bat assembleRelease appDistributionUploadRelease'
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