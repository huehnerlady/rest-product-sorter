package de.huehnerlady.restproductsorter

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ProductRestController {

    @PostMapping(path = ["/products"], consumes = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun sortProducts(@RequestBody products: List<Product>): List<Product> {
        return products
    }
}