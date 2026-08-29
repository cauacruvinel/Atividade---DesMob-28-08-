/*
 * Seção de Plugins:
 * Define as extensões e ferramentas de build externas utilizadas no projeto.
 * O plugin 'android.application' é essencial para transformar o código Kotlin em um APK Android.
 */
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.appquiz"
    compileSdk {
        version = release(37)
    }

    /*
     * Habilita o View Binding, uma funcionalidade que gera classes de ligação para cada arquivo de layout XML.
     * Isso permite acessar as Views de forma segura e direta no código Kotlin, evitando o uso de 'findViewById'.
     */
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.appquiz"
        minSdk = 36
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}



/*
 * Seção de Dependências:
 * Lista todas as bibliotecas externas que o projeto necessita para funcionar.
 * - androidx.*: Componentes essenciais da Jetpack para compatibilidade e recursos modernos.
 * - material: Componentes de design visual do Google (Material Design 3).
 * - test/androidTest: Bibliotecas para testes unitários e de interface (Junit, Espresso).
 */
dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}