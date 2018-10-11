package com.deflatedpickle.wheeze.util

import java.util.*

object Lang {
    val locale = Locale(Config.language, Config.country, Config.operatingSystem)

    val bundle = ResourceBundle.getBundle("lang/lang", locale)
}