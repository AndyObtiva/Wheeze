package com.deflatedpickle.wheeze.brush.custom

import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.GC

import com.deflatedpickle.wheeze.brush.Brush

class OvalBrush extends Brush
    method OvalBrush() public
        super()
        this.name = "Oval"

    method paint(gc=GC, location=Point) public
        gc.drawOval(location.x, location.y, 10, 10)
        gc.fillOval(location.x, location.y, 10, 10)
