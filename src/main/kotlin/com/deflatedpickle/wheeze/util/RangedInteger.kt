package com.deflatedpickle.wheeze.util

import java.io.Serializable

class RangedInteger(val min: Int, current: Int, val max: Int) : Serializable {
    var current: Int = current
        set(value) {
            if (value >= this.min && value <= this.max)
                field = value
        }
}