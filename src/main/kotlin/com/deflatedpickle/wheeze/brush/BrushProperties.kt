package com.deflatedpickle.wheeze.brush

import com.deflatedpickle.wheeze.util.RangedInteger

/**
 * A packet of data to send to the brush scripts.
 */
class BrushProperties(@JvmField var name: String,
                      @JvmField val mode: Brush.Mode,
                      @JvmField val width: RangedInteger,
                      @JvmField val height: RangedInteger,
                      @JvmField val opacity: Int,
                      @JvmField val density: Int,
                      @JvmField val widthPressure: Boolean,
                      @JvmField val heightPressure: Boolean,
                      @JvmField val opacityPressure: Boolean,
                      @JvmField val densityPressure: Boolean)