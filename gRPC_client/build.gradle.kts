import com.google.protobuf.gradle.*

plugins {
    id("java")
    id("com.google.protobuf") version "0.9.4"
    id("idea")
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

val grpcVersion = "1.76.0"

repositories {
    mavenCentral()
}
dependencies {
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("com.google.protobuf:protobuf-java:4.33.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    compileOnly("io.grpc:grpc-netty:")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.33.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
            }
        }
    }
}

sourceSets {
    named("main") {
        java {
            srcDir("build/generated/source/proto/main/grpc")
            srcDir("build/generated/source/proto/main/java")
        }
    }
}

tasks.startScripts {
    enabled = false
}