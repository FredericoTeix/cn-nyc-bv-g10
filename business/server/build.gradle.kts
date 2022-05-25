import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.6.21"
}

group = "ul.fc.mei.cn"

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${rootProject.ext["coroutinesVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext["coroutinesVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${rootProject.ext["coroutinesVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${rootProject.ext["coroutinesVersion"]}")

    //Specific grpc service dependency
    implementation(project(":proto"))

    // Dependencies to be able to work with GRPC
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlinVersion"]}")
    implementation("io.grpc:grpc-protobuf:${rootProject.ext["grpcVersion"]}")
    implementation("com.google.protobuf:protobuf-java-util:${rootProject.ext["protobufVersion"]}")
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")

    //GRPC Spring integration
    implementation("io.github.lognet:grpc-spring-boot-starter:4.7.0")

    implementation("org.springframework.boot:spring-boot-starter-web")

    // MongoDB dependencies
    val mongoVersion = "4.5.1"
    implementation("org.mongodb:bson:$mongoVersion")
    implementation("org.litote.kmongo:kmongo:$mongoVersion")
    implementation("org.litote.kmongo:kmongo-id:$mongoVersion")
    implementation("org.litote.kmongo:kmongo-core:$mongoVersion")
    implementation("org.mongodb:mongodb-driver-sync:$mongoVersion")
    implementation("org.mongodb:mongodb-driver-core:$mongoVersion")

    //Metrics dependencies
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")


    // Spring Dependencies
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ul.fc.mei.cn.BusinessApplicationKt"
    }
}
