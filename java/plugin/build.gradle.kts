plugins {
  java
  kotlin("jvm") version "1.9.24"
  id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.saucelabs"
version = "1.0-SNAPSHOT"

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.junit.jupiter:junit-jupiter:5.9.3")
  implementation("com.saucelabs:saucebindings-junit5:2.0.0-beta.1.1-SNAPSHOT")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2023.2.6")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf("java", "junit"))
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17)) // Ensure Java 17 compatibility
  }
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    changeNotes.set("Adds right-click option to run JUnit Jupiter tests with custom system property.")
    sinceBuild.set("232")
    untilBuild.set("242.*")
  }

  test {
    useJUnitPlatform()
  }
}
