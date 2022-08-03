pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

val tmp = 2
rootProject.name = "viewmodel"

// dependencies
if (System.getenv("INCLUDE_BUILD") == "true") {
    includeBuild("../live")
} else {
    println("including as subprojects")
    includeSubs("live", "../live", "core") // "coroutines", "react", "test"
}

includeSubs("viewmodel", ".", "core") //  "coroutines", "react", "test"
