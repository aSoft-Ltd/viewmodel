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
                    api(asoft.live.coroutines)
                } else {
                    api(project(":live-coroutines"))
                }
                api(projects.viewmodelCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(), description = "An extension of the viewmodel core to be used with coroutines"
)