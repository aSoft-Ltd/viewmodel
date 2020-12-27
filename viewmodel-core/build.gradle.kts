plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    universalLib()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${vers.kotlinx.coroutines}")
                api(asoft("logging-console",vers.asoft.logging))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test-coroutines",vers.asoft.test))
            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.lifecycle:lifecycle-extensions:${vers.androidx.lifecycle}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${vers.kotlinx.coroutines}")
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.viewmodel,
    description = "A multiplatfrom library to handling viewmodel in an MVVMI architecture"
)