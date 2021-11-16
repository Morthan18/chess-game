
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31" // <1>

    application // <2>
}

repositories {
    mavenCentral() // <3>
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom")) // <4>

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // <5>

    implementation("com.google.guava:guava:30.1.1-jre") // <6>

    testImplementation("org.jetbrains.kotlin:kotlin-test") // <7>

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit") // <8>
}

val fatJar = task("fatJar", type = Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "Gradle Jar File Example"
        attributes["Implementation-Version"] = archiveVersion
        attributes["Main-Class"] = "demo.AppKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}
application {
    mainClass.set("demo.AppKt") // <9>
}
