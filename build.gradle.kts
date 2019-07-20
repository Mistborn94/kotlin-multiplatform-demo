repositories {
    jcenter()
}

plugins {
    kotlin("multiplatform") version "1.3.21"
}

kotlin {
    jvm()
    js("web")

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val webMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}

/**
 * Change the bundle type to make it work with npm packages
 */
kotlin.js("web").compilations["main"].kotlinOptions {
    moduleKind = "umd"
}

/**
 * A build step to copy all js files + the package json to the `build/package` directory
 */
task<Sync>("jsPackage") {
    group = "build"
    val output = kotlin.js("web").compilations["main"].output

    //node_modules is preserved for local npm folder installs
    preserve {
        include("node_modules", "package-lock.json")
    }

    output.classesDirs.forEach {
        from(it) {
            include("*.js")
            exclude("*.meta.js")
        }
    }
    from(output.resourcesDir)

    into("$buildDir/package")

    mustRunAfter("webMainClasses")
}

tasks["build"].dependsOn("jsPackage")