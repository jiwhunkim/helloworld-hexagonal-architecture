val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("org.asciidoctor.jvm.convert")
    id("org.asciidoctor.jvm.base")
    id("com.epages.restdocs-api-spec")
}

val asciidoctorExt by configurations.creating

dependencies {
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor:3.0.1")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.1")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.19.2")
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.withType<Test> {
    outputs.dir(snippetsDir)
}

tasks.named<org.asciidoctor.gradle.jvm.AsciidoctorTask>("asciidoctor") {
    dependsOn("test", "intTest")
    configurations(asciidoctorExt.name)
    inputs.dir(snippetsDir)
    baseDirFollowsSourceFile()
}

tasks.bootJar {
    dependsOn("asciidoctor", "openapi3")
    from("${tasks.asciidoctor.get().outputDir}") {
        into("BOOT-INF/classes/static/docs")
    }

    from("${project.rootDir.path}/swagger-ui") {
        into("BOOT-INF/classes/static/swagger")
    }

    from("build/api-spec") {
        into("BOOT-INF/classes/static/swagger")
    }
}
