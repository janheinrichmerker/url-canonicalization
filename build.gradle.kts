plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
    id("org.jetbrains.dokka") version "0.9.17"
    id("com.palantir.git-version") version "3.0.0"
}

val gitVersion: groovy.lang.Closure<String> by extra
group = "dev.reimer"
version = gitVersion()

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.17.2")
    testImplementation("dev.reimer:java-ktx:0.1.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

lateinit var javadocJar: TaskProvider<Jar>
lateinit var sourcesJar: TaskProvider<Jar>

tasks {
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