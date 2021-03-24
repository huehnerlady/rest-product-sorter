package de.huehnerlady.restproductsorter

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class ProductServiceTest : FreeSpec() {


    private val productService = ProductService()

    init {
        "should return same amount of products"{
            val products = listOf(
                createInputProduct("isin1"),
                createInputProduct("isin2")
            )

            val convertedProducts = productService.sortAndConvert(products)

            convertedProducts shouldHaveSize 2
        }

        "should convert products"{
            val products = listOf(createInputProduct("isin1", BigDecimal("1.3445"), "issuerName1", "underlyingName1"))
            val expectedOutput = listOf(
                Product(
                    "isin1",
                    "underlyingName1",
                    "issuerName1",
                    BigDecimal("1.3445")
                )
            )

            val convertedProducts = productService.sortAndConvert(products)

            convertedProducts shouldBe expectedOutput
        }

        "should sort products by yield"{
            val products = listOf(
                createInputProduct("id1", BigDecimal("1")),
                createInputProduct("id2", BigDecimal("2")),
                createInputProduct("id3", BigDecimal("0"))
            )

            val convertedProducts = productService.sortAndConvert(products)

            convertedProducts[0].id shouldBe "id2"
            convertedProducts[1].id shouldBe "id1"
            convertedProducts[2].id shouldBe "id3"
        }
    }

    private fun createInputProduct(
        id: String,
        sideYield: BigDecimal = BigDecimal.ZERO,
        issuerName: String = "someIssuerName",
        underlyingName: String = "someUnderlyingName"
    ): InputProduct {
        return InputProduct(
            derived = Derived(Issuer(issuerName), Underlying(underlyingName)),
            ids = Ids(id),
            figures = Figures(sideYieldPa = sideYield)
        )
    }
}
