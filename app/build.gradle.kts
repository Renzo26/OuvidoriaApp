plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.app.ouvidoriaapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.ouvidoriaapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("com.github.gcacace:signature-pad:1.3.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // ===== DEPENDÊNCIAS DO FIREBASE (ADICIONE ESTAS) =====

    // Firebase BoM (Bill of Materials) - controla versões
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // Firebase Analytics (básico)
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Firebase Authentication (se for usar login)
    implementation("com.google.firebase:firebase-auth-ktx")

    // Firestore Database (banco de dados)
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Cloud Storage (upload de arquivos)
    implementation("com.google.firebase:firebase-storage-ktx")

    // Cloud Messaging (notificações push)
    implementation("com.google.firebase:firebase-messaging-ktx")
}