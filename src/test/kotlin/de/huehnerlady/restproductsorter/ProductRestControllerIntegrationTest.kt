package de.huehnerlady.restproductsorter

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.io.File


@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductRestControllerIntegrationTest(@Value("\${local.server.port}") val port: Int) : FreeSpec() {



    private val restTemplate = RestTemplate()

    private val productsFile = File("src/test/resources/products.json")

    init {

        "products as json" - {
            "should handle empty list"{
                val headers = HttpHeaders().apply {
                    contentType = MULTIPART_FORM_DATA
                }
                val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
                body.add("file", FileSystemResource(productsFile))

                val requestEntity = HttpEntity(body, headers)

                val serverUrl = "http://localhost:$port/products"
                val response = restTemplate.postForEntity(serverUrl, requestEntity, Array<Product>::class.java)

                response shouldNotBe null
            }
        }
    }
}
