import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.6.10"
}

group = "ul.fc.mei.cn"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {

    //Specific grpc service dependency
    implementation(project(":proto"))

    // Dependencies to be able to work with GRPC
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")
    implementation("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlinVersion"]}")
    implementation("io.grpc:grpc-protobuf:${rootProject.ext["grpcVersion"]}")
    implementation("com.google.protobuf:protobuf-java-util:${rootProject.ext["protobufVersion"]}")
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")

    //GRPC Spring integration
    implementation("io.github.lognet:grpc-spring-boot-starter:4.6.0")

    // MongoDB dependencies 
    val mongoVersion = "4.5.0"
    implementation("org.mongodb:mongodb-driver-async:$mongoVersion")
    implementation("org.mongodb:mongodb-driver-core:$mongoVersion")
    implementation("org.litote.kmongo:kmongo-coroutine:$mongoVersion")
    implementation("org.mongodb:bson:$mongoVersion")
    implementation("org.litote.kmongo:kmongo-id:$mongoVersion")

    // Spring Dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
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
