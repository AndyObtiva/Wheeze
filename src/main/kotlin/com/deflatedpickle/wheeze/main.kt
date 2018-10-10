package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.canvas.CanvasHolder
import com.deflatedpickle.wheeze.canvas.CanvasTabs
import com.deflatedpickle.wheeze.toolbars.Hotbar
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

fun main(args: Array<String>) {
    val display = Display()
    val shell = Shell(display)
    shell.text = "Wheeze"
    shell.setMinimumSize(400, 400)

    val layout = FillLayout()
    shell.layout = layout

    val horizontalForm  = SashForm(shell, SWT.HORIZONTAL or SWT.SMOOTH)
    horizontalForm.layoutData = GridData(SWT.FILL, SWT.FILL, true, true)
    horizontalForm.layout = FillLayout()

    val canvasTabs = CanvasTabs(horizontalForm)
    canvasTabs.layout = FillLayout()
    // FIXME: The PenManager is a singleton -- multiple tabs can't be made
    canvasTabs.newCanvas("untitled")
    // canvasTabs.newCanvas("untitled 2")

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