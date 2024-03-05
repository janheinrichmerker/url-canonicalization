plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "dev.reimer"
version = "0.1.1"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jsoup:jsoup:1.17.2")
    testImplementation("dev.reimer:java-ktx:0.1.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

lateinit var javadocJar: TaskProvider<Jar>
lateinit var sourcesJar: TaskProvider<Jar>

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    test {
        useJUnitPlatform()
    }

    // Include project license in generated JARs.
    withType<Jar> {
        from(project.projectDir) {
            include("LICENSE")
            into("META-INF")
        }
    }

    // Generate Kotlin/Java documentation from sources.
    dokka {
        outputFormat = "html"
    }

    // JAR containing Kotlin/Java documentation.
    javadocJar = register<Jar>("javadocJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        dependsOn(dokka)
        from(dokka)
        archiveClassifier.set("javadoc")
    }

    // JAR containing all source files.
    sourcesJar = register<Jar>("sourcesJar") {
        from(sourceSets.main.get().allSource)
        archiveClassifier.set("sources")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
        }
    }
}