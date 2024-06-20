package com.helloworld

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@SpringBootTest(classes = [DomainRdsApplication::class])
class DomainRdsSpringBootSpec : DescribeSpec() {
    init {
        describe("DomainRdsApplication") {
            it("integration") {
                "1".shouldBe("1")
            }
        }
    }
}
