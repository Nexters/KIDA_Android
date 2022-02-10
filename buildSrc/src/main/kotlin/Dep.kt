object Dep {

    const val minSdk = 24
    const val compileSdk = 31
    const val targetSdk = 31

    const val versionCode = 1
    const val versionName = "1.0.0"


    object Plugins {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val dagger = "com.google.dagger:hilt-android-gradle-plugin:${Dagger.version}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:6.2.0"
    }

    object Kotlin {
        const val version = "1.6.10"
        const val coroutineVersion = "1.6.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.0"

        const val material = "com.google.android.material:material:1.4.0"

        const val lifecycleVersion = "2.4.0"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"
        const val lifecycleCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

        const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Room {
        private const val version = "2.4.1"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"

        // test
        const val testing = "androidx.room:room-testing:$version"
    }

    object Dagger {
        const val version = "2.40.5"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
        const val dagger = "com.google.dagger:dagger:$version"
        const val hiltTest = "com.google.dagger:hilt-android-testing:$version"
    }

    object Compose {
        const val version = "1.1.0"

        const val runtime = "androidx.compose.runtime:runtime:$version"

        const val material = "androidx.compose.material:material:$version"
        const val materialIcon = "androidx.compose.material:material-icons-core:$version"
        const val materialIconsExtended =
            "androidx.compose.material:material-icons-extended:$version"

        const val ui = "androidx.compose.ui:ui:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiUtil = "androidx.compose.ui:ui-util:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"

        const val activity = "androidx.activity:activity-compose:1.4.0"

        private const val accompanistVersion = "0.24.1-alpha"
        const val accompanistInset = "com.google.accompanist:accompanist-insets:$accompanistVersion"
        const val accompanistInsetUi =
            "com.google.accompanist:accompanist-insets-ui:$accompanistVersion"
        const val accompanistSystemUi =
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
        const val accompanistIndicator =
            "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
        const val mdc3 = "androidx.compose.material3:material3:1.0.0-alpha02"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.coroutineVersion}"
        const val mockk = "io.mockk:mockk:1.12.2"
    }

    object AndroidTest {
        const val core = "androidx.test:core:1.4.0"
        const val rules = "androidx.test:rules:1.4.0"
        const val runner = "androidx.test:runner:1.4.0"
        const val junitExt = "androidx.test.ext:junit:1.1.3"
        const val compose = "androidx.compose.ui:ui-test:${Compose.version}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"
        const val mockk = "io.mockk:mockk-android:1.12.2"
    }

    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val coil = "io.coil-kt:coil-compose:1.4.0"
}
