plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.6.10"
}

android {
    compileSdk = Dep.compileSdk

    defaultConfig {
        applicationId = "team.nexters.kida"
        minSdk = Dep.minSdk
        targetSdk = Dep.targetSdk
        versionCode = Dep.versionCode
        versionName = Dep.versionName

        testInstrumentationRunner = "team.nexters.kida.HiltRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dep.Compose.version
    }
    packagingOptions {
        resources.excludes.addAll(
            listOf("/META-INF/AL2.0", "/META-INF/LGPL2.1", "META-INF/licenses/**")
        )
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(Dep.Kotlin.stdlib)
    implementation(Dep.Kotlin.coroutine)
    implementation(Dep.Kotlin.serialization)

    implementation(Dep.Android.core)
    implementation(Dep.Android.appcompat)
    implementation(Dep.Android.material)

    implementation(Dep.Compose.ui)
    implementation(Dep.Compose.uiUtil)
    implementation(Dep.Compose.uiTooling)

    implementation(Dep.Compose.foundation)

    implementation(Dep.Compose.material)
    implementation(Dep.Compose.materialIcon)
    implementation(Dep.Compose.materialIconsExtended)

    implementation(Dep.Compose.activity)
    implementation(Dep.Compose.runtime)

    implementation(Dep.Android.lifecycleCompose)

    implementation(Dep.Dagger.hiltAndroid)
    kapt(Dep.Dagger.hiltCompiler)
    implementation(Dep.Android.navigationCompose)
    implementation(Dep.Android.hiltNavigationCompose)

    implementation(Dep.Room.runtime)
    implementation(Dep.Room.ktx)
    ksp(Dep.Room.compiler)

    implementation(Dep.Android.lifecycleViewModel)
    implementation(Dep.Android.lifecycleRuntime)
    implementation(Dep.Android.lifecycleSavedState)

    implementation(Dep.timber)

    implementation(Dep.Compose.accompanistInset)
    implementation(Dep.Compose.accompanistInsetUi)
    implementation(Dep.Compose.accompanistSystemUi)
    implementation(Dep.Compose.accompanistPager)
    implementation(Dep.Compose.accompanistIndicator)

    testImplementation(Dep.Test.junit)
    testImplementation(Dep.Test.coroutine)
    testImplementation(Dep.Test.mockk)
    testImplementation(Dep.Dagger.hiltTest)
    testImplementation(Dep.Room.testing)
    kaptTest(Dep.Dagger.hiltCompiler)

    androidTestImplementation(Dep.AndroidTest.junitExt)
    androidTestImplementation(Dep.AndroidTest.core)
    androidTestImplementation(Dep.AndroidTest.rules)
    androidTestImplementation(Dep.AndroidTest.runner)
    androidTestImplementation(Dep.AndroidTest.compose)
    androidTestImplementation(Dep.AndroidTest.composeJunit)
    androidTestImplementation(Dep.Dagger.hiltTest)
    kaptAndroidTest(Dep.Dagger.hiltCompiler)

    debugImplementation(Dep.AndroidTest.composeManifest)
}
