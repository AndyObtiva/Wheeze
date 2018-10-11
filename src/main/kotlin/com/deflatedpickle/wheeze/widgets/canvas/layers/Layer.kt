package com.deflatedpickle.wheeze.widgets.canvas.layers

import org.eclipse.swt.graphics.GC

class Layer(parent: LayeredCanvas) {
    val gc = GC(parent)

    var index = parent.layers.size

    var isHidden = false
    var isLocked = false
}