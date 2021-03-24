package de.huehnerlady.restproductsorter

import java.math.BigDecimal


data class Product(
    val _id: String,
    val derived: Derived,
    val ids: Ids,
    val figures: Figures
)

data class Derived(val issuer: Issuer, val underlying: Underlying)
data class Issuer(val name: String)
data class Underlying(val name: String)
data class Ids(
    val isin: String? = null,
    val wkn: String? = null,
    val vwd: String? = null
)
data class Figures(val sideYield: BigDecimal, val sideYieldPa: BigDecimal)
