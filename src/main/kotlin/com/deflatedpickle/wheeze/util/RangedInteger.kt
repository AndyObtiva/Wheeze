package com.deflatedpickle.wheeze.util

class RangedInteger(val min: Int, current: Int, val max: Int) {
    var current: Int = current
        set(value) {
            if (value >= this.min && value <= this.max)
                field = value
        }
}