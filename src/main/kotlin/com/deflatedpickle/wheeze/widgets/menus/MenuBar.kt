package com.deflatedpickle.wheeze.widgets.menus

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Menu
import org.eclipse.swt.widgets.Shell

class MenuBar(parent: Shell) {
    val menu = Menu(parent, SWT.BAR)

    val fileMenu = FileMenu(menu)
}