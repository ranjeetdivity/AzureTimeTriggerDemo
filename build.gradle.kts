import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.3.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    id("com.microsoft.azure.azurefunctions") version "1.5.0"

}

group = "cloud.practice"
version = "0.0.2-SNAPSHOT"
val appName = "azureTimeTriggerDemo"
val appVer = version
java.sourceCompatibility = JavaVersion.VERSION_11
val javaVer = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.microsoft.azure.functions","azure-functions-java-library", "1.4.0")
    implementation ("org.springframework.cloud","spring-cloud-function-adapter-azure","3.0.10.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}



java {
    sourceCompatibility = javaVer
    targetCompatibility = javaVer
}

springBoot {
    buildInfo {
        properties {
            artifact = "$appName-$appVer.jar"
            version = version
            name = appName
        }
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
azurefunctions {

    subscription = "aa-ct-nonprod-spoke"
    resourceGroup = "ct-n-zeaus-cbncln-rg"
    appName = "Azure-Time-Triger-Demo"
    region = "westus"
}

tasks.bootJar {enabled = true}
tasks.jar {enabled = true}
tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar>().configureEach {
    launchScript()
}
