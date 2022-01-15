rootProject.name = "helloworld-hexagonal-architecture"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include(
    "helloworld-hexagonal-domain-rds",
    "helloworld-hexagonal-domain-redis",
    "helloworld-hexagonal-domain",
    "helloworld-hexagonal-external-api",
    "client:coupon"
)
