pipeline {
    agent any

    environment {
        GOOGLE_APPLICATION_CREDENTIALS = 'D:\\vaibhav\\jenkins\\jenkins-distribution-service-credential.json'
        SONARQUBE = credentials('sqa_727c274f19dc8ffbed832b57f171ebe724a3bee2')
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

        stage('Archive APK') {
            steps {
                // Archiving the release APK
                archiveArtifacts artifacts: '**/build/app/outputs/flutter-apk/app-release.apk', allowEmptyArchive: false
            }
        }

        stage ('Distribute') {
            steps {
                // Running the Gradle commands with the environment variable set
                bat 'cd android && gradlew.bat assembleRelease appDistributionUploadRelease'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Get the SonarScanner installation path (name must match Jenkins tool configuration)
                    def scannerHome = tool 'SonarScanner'

                    // Run SonarScanner using Windows batch
                    withSonarQubeEnv('SonarQube') {
                        bat """
                        "${scannerHome}\\bin\\sonar-scanner.bat" ^
                        -Dsonar.host.url=%SONAR_HOST_URL% ^
                        -Dsonar.login=%SONAR_AUTH_TOKEN%
                        """
                    }
                }
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