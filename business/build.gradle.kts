group = "ul.fc.mei.cn"

plugins {
    kotlin("jvm") version "1.6.10"
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

ext["grpcVersion"] = "1.45.1" // need to wait for grpc kotlin to move past this
ext["grpcKotlinVersion"] = "1.2.1" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.19.4"
ext["coroutinesVersion"] = "1.6.1"