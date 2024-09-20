package com.helloworld.external.web.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun api(
        @Value("\${springdoc.version}") appVersion: String,
    ): OpenAPI {
        return OpenAPI().components(Components())
            .info(
                Info().title("Hello API").version(appVersion)
                    .license(License().name("Apache 2.0").url("http://springdoc.org")),
            )
    }
}
