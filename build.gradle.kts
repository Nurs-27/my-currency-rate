plugins {
    id("idea")
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

idea {
    project {
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(JavaVersion.VERSION_11)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

val javaVersion = "11"
val springBootVersion = "2.6.2"

allprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "ru.otus"
    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
    }

    dependencies {
        /** Spring **/
        implementation("org.springframework.boot:spring-boot-starter-web")

        /** Kotlin **/
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        /** Klogging **/
        implementation("io.github.microutils:kotlin-logging:2.1.21")

    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

