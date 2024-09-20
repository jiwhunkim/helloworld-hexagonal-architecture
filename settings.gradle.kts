rootProject.name = "helloworld-hexagonal-architecture"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include(
    "data:mysql",
    "data:redis",
    ":domain",
    ":application",
    ":external-api",
    "clients:coupon"
)
