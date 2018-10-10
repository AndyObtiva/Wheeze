package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.widgets.Widgets
import com.deflatedpickle.wheeze.widgets.canvas.CanvasTabs
import com.deflatedpickle.wheeze.widgets.menus.MenuBar
import com.deflatedpickle.wheeze.widgets.toolbars.hotbar.HotBar
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Shell

fun main(args: Array<String>) {
    val display = Widgets.display
    val shell = Widgets.shell
    shell.text = "Wheeze"
    shell.setMinimumSize(400, 400)
    shell.layout = GridLayout()

    val menu = Widgets.menu
    shell.menuBar = menu.menu

    val hotbar = Widgets.hotbar
    hotbar.layout = FillLayout()
    hotbar.layoutData = GridData(SWT.FILL, SWT.FILL, true, false)
    hotbar.pack()

    val horizontalForm = Widgets.horizontalForm
    horizontalForm.layoutData = GridData(SWT.FILL, SWT.FILL, true, true)
    horizontalForm.layout = FillLayout()

    val canvasTabs = Widgets.canvasTabs
    canvasTabs.layout = FillLayout()
    // FIXME: The PenManager is a singleton -- multiple tabs can't be made
    // canvasTabs.newCanvas("untitled")
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