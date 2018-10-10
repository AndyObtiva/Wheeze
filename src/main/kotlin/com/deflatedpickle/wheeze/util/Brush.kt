package com.deflatedpickle.wheeze.util

import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.RGB
import org.eclipse.swt.widgets.Display
import java.awt.Color as AColor

class Brush(display: Display,
            var size: Int = 10,
            colourRGB: RGB = RGB(0, 0, 0),
            var opacity: Int = 255) {
    var colour = Color(display, colourRGB.red, colourRGB.green, colourRGB.blue)

    // TODO: Add methods to save brushes to and load them from JSON files
}