package de.huehnerlady.restproductsorter

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

@WebMvcTest
class ProductRestControllerTest : FreeSpec() {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean(relaxed = true)
    private lateinit var productService: ProductService

    private val objectMapper = ObjectMapper()

    init {

        "should handle empty list"{
            mockMvc.post("/products") {
                contentType = APPLICATION_JSON
                content = "[]"
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content { json("[]") }
            }
        }

        "should call productService"{
            val expectedProducts = listOf(
                InputProduct(
                    derived = Derived(Issuer("issuerName"), Underlying("underlyingName")),
                    ids = Ids("isin"),
                    figures = Figures(sideYieldPa = BigDecimal("123.45"))
                )
            )
            mockMvc.post("/products") {
                contentType = APPLICATION_JSON
                content = expectedProducts.toJson()
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }

            verify(exactly = 1) { productService.sortAndConvert(expectedProducts) }
        }

        "should return response from productService"{
            val expectedResponse = listOf(Product("id", "someName", "someIssuer", BigDecimal("1.5678")))
            every{
                productService.sortAndConvert(any())
            } returns expectedResponse
            mockMvc.post("/products") {
                contentType = APPLICATION_JSON
                content = "[]"
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content { json(expectedResponse.toJson()) }
            }

        }
    }

    private fun List<Any>.toJson() = objectMapper.writeValueAsString(this)
}
