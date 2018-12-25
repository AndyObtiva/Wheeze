package com.deflatedpickle.wheeze.brush.custom

import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.GC

import com.deflatedpickle.wheeze.brush.BrushScript
import com.deflatedpickle.wheeze.widgets.PaintableCanvas
import com.deflatedpickle.wheeze.brush.BrushProperties

class RectangleBrush extends BrushScript
    method RectangleBrush() public
        this.name = "Rectangle"

    method paint(canvas=PaintableCanvas, location=Point, properties=BrushProperties) public
        canvas.paintGC.drawRectangle(location.x, location.y, properties.width.getCurrent(), properties.height.getCurrent())
        canvas.paintGC.fillRectangle(location.x, location.y, properties.width.getCurrent() + 1, properties.height.getCurrent() + 1)
