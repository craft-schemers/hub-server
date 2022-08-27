import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.endlesscode.bukkitgradle.dependencies.spigotApi

plugins {
    kotlin("jvm") version "1.7.0"
    id("ru.endlesscode.bukkitgradle") version "0.10.1"
}

group = "com.craftschemers"
description = "Hub Server"
version = "0.1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly(spigotApi)

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.18:2.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = "com.craftschemers.hub.Hub"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

bukkit {
    apiVersion = "1.19"
    meta {
        name.set("Hub")
        description.set("Hub Server")
        main.set("com.craftschemers.hub.Hub")
    }
    server {
        coreType = ru.endlesscode.bukkitgradle.server.extension.CoreType.PAPER
        eula = true
        onlineMode = false
        debug = true
        encoding = "UTF-8"
        javaArgs("-Xmx1G")
        bukkitArgs("nogui")
    }
}
