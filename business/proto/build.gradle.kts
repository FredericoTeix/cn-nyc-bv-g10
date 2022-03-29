import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*

group = "ul.fc.mei.cn"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
    id("com.google.protobuf") version "0.8.15"
    id("idea")
}



repositories {
    mavenCentral()
}

tasks.test {
    useJUnit()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

dependencies {
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlinVersion"]}")
    implementation("io.grpc:grpc-stub:${rootProject.ext["grpcVersion"]}")
    implementation("io.grpc:grpc-protobuf:1.44.1")

    implementation("com.google.protobuf:protobuf-java-util:3.19.4")
    implementation("com.google.protobuf:protobuf-kotlin:3.19.4")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
}

repositories {
    google()
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
    dependsOn += "generateProto"
}

sourceSets {
    main {
        proto {
            this@proto.srcDir("src/main/proto")
        }
    }
}

tasks["clean"].doLast {
    delete("./gen")
}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.jar {

    // To add all of the dependencies otherwise a "NoClassDefFoundError" error
    from(sourceSets.main.get().output)


    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}


protobuf {
    generatedFilesBaseDir = "$projectDir/gen"

    protoc {
        artifact = "com.google.protobuf:protoc:${project.ext["protobufVersion"]}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${project.ext["grpcVersion"]}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${project.ext["grpcKotlinVersion"]}:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

