package com.helloworld.external

import com.helloworld.MysqlContainerConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@Import(MysqlContainerConfig::class)
class ExternalApplicationSpec: DescribeSpec() {
    init {
        it("hello") {
            "1".shouldBe("1")
        }
    }
}
