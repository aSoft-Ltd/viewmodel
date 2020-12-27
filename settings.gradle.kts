pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "viewmodel"
include(":viewmodel-core")
include(":viewmodel-react")

include(":viewmodel-test-core")
project(":viewmodel-test-core").projectDir = File("viewmodel-test/viewmodel-test-core")
include(":viewmodel-test-expect")
project(":viewmodel-test-expect").projectDir = File("viewmodel-test/viewmodel-test-expect")
