package com.deflatedpickle.wheeze.widgets

import com.deflatedpickle.wheeze.widgets.canvas.CanvasTabs
import com.deflatedpickle.wheeze.widgets.menus.MenuBar
import com.deflatedpickle.wheeze.widgets.toolbars.hotbar.HotBar
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

object Widgets {
    val display = Display()
    val shell = Shell(display)

    val menu = MenuBar(shell)

    val hotbar = HotBar(shell)

    val horizontalForm = SashForm(shell, SWT.HORIZONTAL or SWT.SMOOTH)

    val canvasTabs = CanvasTabs(horizontalForm)
}