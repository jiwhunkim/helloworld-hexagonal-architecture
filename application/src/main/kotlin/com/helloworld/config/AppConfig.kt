package com.helloworld.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    lazyInit = true,
    basePackages = ["com.helloworld.config", "com.helloworld.order"]
)
class AppConfig
