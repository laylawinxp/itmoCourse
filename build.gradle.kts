plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-schema-validator:5.4.0")

    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")

    testImplementation("io.qameta.allure:allure-rest-assured:2.24.0")
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")

    testImplementation("org.hamcrest:hamcrest:2.2")

    testImplementation("net.datafaker:datafaker:2.1.0")

    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.test {
    useJUnitPlatform()
}