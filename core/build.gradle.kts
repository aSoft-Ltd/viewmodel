plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets(true)
//    val nativeTargets = listOf(
//        macosArm64(), macosX64(), iosArm64(), iosX64(), linuxX64()
//    )
    val nativeTargets = linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                api(kotlinx.serialization.json)
                api(projects.cacheApi)
                api(projects.cacheMock)
                api(projects.lexiConsole)
                api(projects.krestApi)
                api(projects.kaseCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.expectCoroutines)
            }
        }

        val androidMain by getting {
            dependencies {
                api(androidx.lifecycle.viewmodel.ktx)
            }
        }


        val nonAndroidMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(nonAndroidMain)
        }

        val jsMain by getting {
            dependsOn(nonAndroidMain)
        }

        for (target in nativeTargets) {
            val main by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(nonAndroidMain)
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A multiplatfrom library for authoring viewmodel in an MVIVM architecture"
)