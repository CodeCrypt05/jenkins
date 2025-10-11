plugins {
    id("com.android.application")
    // START: FlutterFire Configuration
    id("com.google.gms.google-services")
    // END: FlutterFire Configuration
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
    // App distibuiton plugin
    id ("com.google.firebase.appdistribution")
}

android {
    namespace = "com.example.jenkins"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.jenkins"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
         getByName("release") {
            // Your existing release config
            isMinifyEnabled = false
            isShrinkResources = false 
            
            // Add Firebase App Distribution configuration
            firebaseAppDistribution {
                // Your Firebase App ID
                appId = "1:354531812719:android:65c2c28932e0aaf580b49a"
                
                // Use groups parameter with your tester group
                groups = "dev-sanity-testing"
                
                // Or if using multiple groups:
                // groups = "dev-sanity-testing, qa-team"
                
                // Release notes from file
                releaseNotesFile = "release-notes.txt"
                
                // Or inline release notes:
                // releaseNotes = "Build from Jenkins - latest features"
                
                // Artifact type
                artifactType = "APK"
            }
        }
        
        getByName("debug") {
            firebaseAppDistribution {
                appId = "1:354531812719:android:65c2c28932e0aaf580b49a"
                groups = "dev-sanity-testing"
                releaseNotes = "Debug build"
                artifactType = "APK"
            }
        }
    }
}

flutter {
    source = "../.."
}
