package com.helloworld.config.mysql.audit

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@TestConfiguration
@EnableJpaAuditing
class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> = Optional.of("SYSTEM")
}
