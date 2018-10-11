package com.deflatedpickle.wheeze.util

import java.util.*

object Config {
    private val inputStream = javaClass.classLoader.getResourceAsStream("config.properties")
    private val properties = Properties()

    init {
        properties.load(inputStream)
    }

    val language = properties.getProperty("LANGUAGE")
    val country = properties.getProperty("COUNTRY")

    val operatingSystem = properties.getProperty("OPERATING_SYSTEM")
}