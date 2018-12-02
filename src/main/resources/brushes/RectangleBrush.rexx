package com.deflatedpickle.wheeze.brush.custom

import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.GC

import com.deflatedpickle.wheeze.brush.Brush

class RectangleBrush extends Brush
    method RectangleBrush() public
        super()
        this.name = "Rectangle"

    method paint(gc=GC, location=Point) public
        gc.drawRectangle(location.x, location.y, 10, 10)
        gc.fillRectangle(location.x, location.y, 10, 10)
