package com.helloworld

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@SpringBootTest(classes = [MysqlApplication::class])
@Import(MysqlContainerConfig::class)
class MysqlApplicationSpec : DescribeSpec() {
    init {
        describe("MysqlApplication") {
            it("integration") {
                "1".shouldBe("1")
            }
        }
    }
}
