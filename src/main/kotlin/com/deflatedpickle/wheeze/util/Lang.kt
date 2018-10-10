package com.deflatedpickle.wheeze.util

import java.util.*

object Lang {
    const val operatingSystem = "WINDOWS"

    val locale = Locale("en", "GB", operatingSystem)

    val bundle = ResourceBundle.getBundle("lang/lang", locale)
}