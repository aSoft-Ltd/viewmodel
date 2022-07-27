plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { library() }
    sourceSets {
        val main by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    api(asoft.live.react)
                } else {
                    api(project(":live-react"))
                }
                api(projects.viewmodelCore)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin/js library to handle viewmodels in an MVVMI architecture from react"
)