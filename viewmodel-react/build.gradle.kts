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
                api(asoft("reakt-core", vers.asoft.reakt))
                api(project(":viewmodel-core"))
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.viewmodel,
    description = "A multiplatfrom library to handling viewmodel in an MVVMI architecture with kotlin/react"
)