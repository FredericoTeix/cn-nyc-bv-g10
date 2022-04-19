group = "ul.fc.mei.cn"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.6.10"
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

ext["grpcVersion"] = "1.43.2" // need to wait for grpc kotlin to move past this
ext["grpcKotlinVersion"] = "1.2.1" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.19.4"
ext["coroutinesVersion"] = "1.5.2"