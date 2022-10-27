description = "Ktor network utilities"

kotlin {
    createCInterop("network", nixTargets()) {
        defFile = projectDir.resolve("nix/interop/network.def")
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.7.20")
            }
        }

        jvmAndNixMain {
            dependencies {
                api(project(":ktor-utils"))
            }
        }

        jvmAndNixTest {
            dependencies {
                api(project(":ktor-test-dispatcher"))
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.mockk)
            }
        }
    }
}
