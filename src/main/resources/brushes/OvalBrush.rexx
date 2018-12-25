package com.deflatedpickle.wheeze.brush.custom

import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.GC

import com.deflatedpickle.wheeze.brush.BrushScript
import com.deflatedpickle.wheeze.widgets.PaintableCanvas
import com.deflatedpickle.wheeze.brush.BrushProperties

class OvalBrush extends BrushScript
    method OvalBrush() public
        this.name = "Oval"

    method paint(canvas=PaintableCanvas, location=Point, properties=BrushProperties) public
        canvas.paintGC.drawOval(location.x, location.y, properties.width.getCurrent() - 1, properties.height.getCurrent() - 1)
        canvas.paintGC.fillOval(location.x - 1, location.y - 1, properties.width.getCurrent() + 2, properties.height.getCurrent() + 2)
