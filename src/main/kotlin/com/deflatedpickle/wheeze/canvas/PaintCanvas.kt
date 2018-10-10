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

class PaintCanvas(parent: Composite) : Composite(parent, SWT.NONE) {
    val canvas = Canvas(this, SWT.DOUBLE_BUFFERED)
    var canvasBackground: Color = Display.getDefault().getSystemColor(SWT.COLOR_WHITE)

    private val canvasLayoutData = GridData(SWT.CENTER, SWT.CENTER, true, true)

    private val penManager = PenManager(ControlPenOwner(canvas))

    val brushList = mutableListOf(Brush(parent.display))
    var activeBrush = brushList[0]

    var paintTool = PaintTools.BRUSH

    var brushSizeDefault = 10

    var cursorLocation = Point(0, 0)

    var doDraw = false

    val paintGC = GC(canvas)
    private val paintListener = PaintListener {
        it.gc.antialias = SWT.ON
    }

    init {
        canvas.layoutData = canvasLayoutData

        canvas.background = canvasBackground

        canvas.addPaintListener(paintListener)

        canvas.addMouseListener(object : MouseListener {
            override fun mouseDoubleClick(e: MouseEvent) { }

            override fun mouseDown(e: MouseEvent) {
                canvas.setFocus()

                doDraw = true

                activeBrush.size = brushSizeDefault
                activeBrush.opacity = 255
                if (canvas.display.focusControl != null) {
                    doPaint(activeBrush.size, activeBrush.colour, cursorLocation)
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
                    doPaint(activeBrush.size, activeBrush.colour, cursorLocation)
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
                    activeBrush.size = (e.levels[0].value * 10).toInt()
                    activeBrush.opacity = (e.levels[0].value * 255).toInt()
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
                    gc.background = activeBrush.colour
                }

                gc.alpha = activeBrush.opacity

                gc.fillOval(location.x, location.y, size, size)
            }
        }))
    }

    fun setCanvasSize(width: Int, height: Int) {
        canvasLayoutData.widthHint = width
        canvasLayoutData.heightHint = height
    }
}