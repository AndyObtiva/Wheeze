package com.deflatedpickle.wheeze.widgets.canvas

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.TabFolder

class CanvasHolder(parent: TabFolder) : Composite(parent, SWT.BORDER) {
    val canvas = PaintCanvas(this)

    init {
        background = Display.getDefault().getSystemColor(SWT.COLOR_GRAY)

        canvas.layout = GridLayout(1, false)
        canvas.setCanvasSize(500, 300)
    }
}