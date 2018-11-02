package com.deflatedpickle.wheeze.widgets.canvas.layers

import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

class LayeredCanvas(parent: Composite, style: Int) : Canvas(parent, style) {
    val layers = mutableListOf<Layer>()

    var selectedLayer = 0

    fun addLayer(): Layer {
        layers.add(Layer(this))

        return layers[layers.size - 1]
    }
}