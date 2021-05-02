plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    val darwinTargets = listOf(
        macosX64(),
        iosArm64(),
        iosArm32(),
        iosX64(),
        watchosArm32(),
        watchosArm64(),
        watchosX86(),
        tvosArm64(),
        tvosX64()
    )

    val linuxTargets = listOf(
        linuxX64()
    )
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
                api(project(":viewmodel-core"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.viewmodel,
    description = "A multiplatfrom library to help test viewmodels"
)