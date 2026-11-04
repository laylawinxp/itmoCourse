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

    testImplementation("com.codeborne:selenide:7.2.2")
}

tasks.test {
    useJUnitPlatform()
    System.getProperties().forEach { key, value ->
        if (key.toString().startsWith("selenide.")) {
            systemProperty(key.toString(), value.toString())
        }
    }
}