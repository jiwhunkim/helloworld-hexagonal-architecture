package com.helloworld.application.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    lazyInit = true,
    basePackages = ["com.helloworld.application"]
)
class AppConfig
