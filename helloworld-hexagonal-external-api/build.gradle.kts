plugins {
}

dependencyManagement {
    imports {
        mavenBom(Libs.Boms.springCloudBom)
    }
}

dependencies {
    implementation(project(":helloworld-hexagonal-domain"))

    implementation(Libs.SpringBoot.starterValidator)
    implementation(Libs.SpringBoot.starterWeb)
    implementation(Libs.SpringBoot.starterActuator)
    implementation(Libs.micrometerRegistryPrometheus)

    implementation(Libs.Springdoc.springdocOpenapiStarterWebmvcUi)
}
