package com.deflatedpickle.wheeze.brush.custom

import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.GC

import com.deflatedpickle.wheeze.brush.BrushScript
import com.deflatedpickle.wheeze.brush.BrushProperties

class RectangleBrush extends BrushScript
    method RectangleBrush() public
        this.name = "Rectangle"

    method paint(gc=GC, location=Point, properties=BrushProperties) public
        gc.drawRectangle(location.x, location.y, properties.width.getCurrent(), properties.height.getCurrent())
        gc.fillRectangle(location.x, location.y, properties.width.getCurrent() + 1, properties.height.getCurrent() + 1)
