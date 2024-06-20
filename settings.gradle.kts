rootProject.name = "helloworld-hexagonal-architecture"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include(
    "data:rds",
    "data:redis",
    ":domain",
    ":application",
    ":external-api",
    "clients:coupon"
)
