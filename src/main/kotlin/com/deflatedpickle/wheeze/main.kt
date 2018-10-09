package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.canvas.PaintCanvas
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

fun main(args: Array<String>) {
    val display = Display()
    val shell = Shell(display)
    shell.text = "Wheeze"

    val layout = FillLayout()
    shell.layout = layout

    val horizontalForm  = SashForm(shell, SWT.HORIZONTAL or SWT.SMOOTH)

    val paintCanvas = PaintCanvas(horizontalForm)
    paintCanvas.layout = GridLayout()

    horizontalForm.pack()

    shell.pack()
    shell.open()

    while (!shell.isDisposed) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }
    display.dispose()
}