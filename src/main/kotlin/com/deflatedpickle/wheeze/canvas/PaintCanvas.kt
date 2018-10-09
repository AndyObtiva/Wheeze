package com.deflatedpickle.wheeze.canvas

import com.deflatedpickle.wheeze.canvas.pen.ControlPenOwner
import jpen.*
import jpen.event.PenAdapter
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
    var canvasBackground: Color = Display.getDefault().getSystemColor(SWT.COLOR_WHITE)

    val penManager = PenManager(ControlPenOwner(canvas))

    var brushSizeDefault = 10
    var brushSize = brushSizeDefault

    var brushColour: Color = Display.getDefault().getSystemColor(SWT.COLOR_BLACK)

    var brushOpacity = 255

    var paintTool = PaintTools.BRUSH

    var cursorLocation = Point(0, 0)

    var doDraw = false

    val paintGC = GC(canvas)
    private val paintListener = PaintListener {
        it.gc.antialias = SWT.ON
    }

    init {
        val canvasLayoutData = GridData(GridData.FILL_BOTH)
        canvas.layoutData = canvasLayoutData

        canvas.background = canvasBackground

        canvas.addPaintListener(paintListener)

        canvas.addMouseListener(object : MouseListener {
            override fun mouseDoubleClick(e: MouseEvent) { }

            override fun mouseDown(e: MouseEvent) {
                doDraw = true

                brushSize = brushSizeDefault
                brushOpacity = 255
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

        penManager.pen.addListener(object : PenAdapter() {
            override fun penLevelEvent(e: PLevelEvent) {
                if (e.levels[0].type == PLevel.Type.PRESSURE) {
                    brushSize = (e.levels[0].value * 10).toInt()
                    brushOpacity = (e.levels[0].value * 255).toInt()
                }
            }

            override fun penKindEvent(e: PKindEvent) {
                when (e.kind.type) {
                    PKind.Type.STYLUS -> {
                        paintTool = PaintTools.BRUSH
                    }
                    PKind.Type.ERASER -> {
                        paintTool = PaintTools.ERASER
                    }
                    else -> { }
                }
            }
        })
    }

    fun doPaint(size: Int, colour: Color, location: Point) {
        paintListener.paintControl(PaintEvent(object : Event() {
            init {
                widget = canvas
                gc = paintGC

                if (paintTool == PaintTools.ERASER) {
                    gc.background = canvasBackground
                }
                else {
                    gc.background = colour
                }

                gc.alpha = brushOpacity

                gc.fillOval(location.x, location.y, size, size)
            }
        }))
    }
}