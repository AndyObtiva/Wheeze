package com.deflatedpickle.wheeze.brush

import com.deflatedpickle.wheeze.util.RangedInteger
import java.io.Serializable

/**
 * A packet of data to send to the brush scripts.
 */
class BrushProperties(@JvmField var name: String,
                      @JvmField var mode: Brush.Mode,
                      @JvmField var width: RangedInteger,
                      @JvmField var height: RangedInteger,
                      @JvmField var opacity: Int,
                      @JvmField var density: Int,
                      @JvmField var widthPressure: Boolean,
                      @JvmField var heightPressure: Boolean,
                      @JvmField var opacityPressure: Boolean,
                      @JvmField var densityPressure: Boolean) : Serializable