package com.deflatedpickle.wheeze.canvas

import org.eclipse.swt.SWT
import org.eclipse.swt.events.*
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.GC
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Event

class PaintCanvas(parent: Composite) : Composite(parent, SWT.BORDER) {
    val canvas = Canvas(this, SWT.NONE)

    var brushSize = 10
    var brushColour: Color = Display.getDefault().getSystemColor(SWT.COLOR_BLACK)

    var cursorLocation = Point(0, 0)

    var doDraw = false

    val paintGC = GC(canvas)
    private val paintListener = PaintListener {
        it.gc.antialias = SWT.ON
    }

    init {
        val canvasLayoutData = GridData(GridData.FILL_BOTH)
        canvas.layoutData = canvasLayoutData

        canvas.background = Display.getDefault().getSystemColor(SWT.COLOR_WHITE)

        canvas.addPaintListener(paintListener)

        canvas.addMouseListener(object : MouseListener {
            override fun mouseDoubleClick(e: MouseEvent) { }

            override fun mouseDown(e: MouseEvent) {
                doDraw = true
                
                if (canvas.display.focusControl != null) {
                    doPaint(brushSize, brushColour, cursorLocation)
                }
            }

            override fun mouseUp(e: MouseEvent) {
                doDraw = false
            }
        })

        canvas.addMouseMoveListener {
            if (canvas.display.focusControl != null) {
                cursorLocation = canvas.display.focusControl.toControl(canvas.display.cursorLocation)

                if (doDraw) {
                    doPaint(brushSize, brushColour, cursorLocation)
                }
            }
        }

        canvas.addFocusListener(object : FocusListener {
            override fun focusLost(e: FocusEvent?) { }

            override fun focusGained(e: FocusEvent) {
                cursorLocation = canvas.display.focusControl.toControl(canvas.display.cursorLocation)
            }
        })
    }

    fun doPaint(size: Int, colour: Color, location: Point) {
        // println(cursorLocation)

        paintListener.paintControl(PaintEvent(object : Event() {
            init {
                widget = canvas
                gc = paintGC

                gc.background = colour
                gc.fillOval(location.x, location.y, size, size)
            }
        }))
    }
}