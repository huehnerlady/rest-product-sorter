package de.huehnerlady.restproductsorter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestProductSorterApplication

fun main(args: Array<String>) {
	runApplication<RestProductSorterApplication>(*args)
}
