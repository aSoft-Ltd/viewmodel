plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    api(asoft.live.test)
                } else {
                    api(project(":live-test"))
                }
                api(projects.viewmodelCore)
                api(asoft.expect.coroutines)
                api(asoft.kotlinx.collections.atomic)
                api(kotlinx.coroutines.test)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A multiplatfrom library to help test viewmodels with the expect library"
)