package de.huehnerlady.restproductsorter

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.spring.SpringListener

class KotestConfig : AbstractProjectConfig() {

    override val isolationMode = IsolationMode.InstancePerLeaf
    override fun listeners() = listOf(SpringListener)

}
