plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt") // Adicionando o plugin kapt
}

android {
    namespace = "com.example.gerenciamentodejogos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gerenciamentodejogos"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // ViewModel and LiveData
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler) // KAPT necessário para processar o Room e gerar o código de implementação

    // ViewModel para gerenciamento de estado
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // Para integração com o ciclo de vida da Activity/Fragment
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Caso esteja usando Jetpack Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // LiveData (caso precise observar dados)
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
}