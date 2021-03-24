package de.huehnerlady.restproductsorter

import java.math.BigDecimal


data class InputProduct(
    val _id: String? = null,
    val derived: Derived,
    val ids: Ids,
    val figures: Figures
)

data class Derived(val issuer: Issuer, val underlying: Underlying)
data class Issuer(val name: String)
data class Underlying(val name: String)
data class Ids(
    val isin: String,
    val wkn: String? = null,
    val vwd: String? = null
)
data class Figures(val sideYield: BigDecimal? = null, val sideYieldPa: BigDecimal)

data class Product(
    val id: String,
    val underlyingName: String,
    val issuer: String,
    val yield: BigDecimal
)