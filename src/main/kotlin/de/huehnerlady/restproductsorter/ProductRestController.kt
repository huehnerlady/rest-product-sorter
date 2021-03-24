package de.huehnerlady.restproductsorter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

@Controller
class ProductRestController(val productService: ProductService) {


    private val objectMapper = ObjectMapper()
        .registerModule(KotlinModule())

    @PostMapping(path = ["/products"], consumes = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun sortProducts(@RequestBody inputProducts: List<InputProduct>): List<Product> {
        return productService.sortAndConvert(inputProducts)
    }

    @PostMapping(path = ["/products"], consumes = [MULTIPART_FORM_DATA_VALUE])
    @ResponseBody
    fun sortProductsWithFile(@RequestPart file: MultipartFile): List<Product> {
        return productService.sortAndConvert(objectMapper.readValue(file.bytes))
    }
}