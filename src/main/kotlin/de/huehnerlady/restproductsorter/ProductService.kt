package de.huehnerlady.restproductsorter

import org.springframework.stereotype.Service

@Service
class ProductService {

    fun sortAndConvert(products: List<InputProduct>): List<Product> {
        return products
            .map {
                Product(
                    id = it.ids.isin,
                    underlyingName = it.derived.underlying.name,
                    issuer = it.derived.issuer.name,
                    yield = it.figures.sideYieldPa
                )
            }
            .sortedByDescending { it.yield }
    }
}