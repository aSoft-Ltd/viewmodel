plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
                api(asoft("kotlinx-atomic-collections",vers.asoft.collections))
                api(project(":viewmodel-test-core"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.viewmodel,
    description = "A multiplatfrom library to help test viewmodels"
)