package com.deflatedpickle.wheeze.widgets.menus

import com.deflatedpickle.wheeze.util.CommonCommands
import com.deflatedpickle.wheeze.util.Lang
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Menu
import org.eclipse.swt.widgets.MenuItem

class FileMenu(parent: Menu) {
    val menuItem = MenuItem(parent, SWT.CASCADE)

    val menu = Menu(parent.shell, SWT.DROP_DOWN)

    init {
        menuItem.text = Lang.bundle.getString("menu.file.name")
        menuItem.menu = menu

        val newButton = MenuItem(menu, SWT.PUSH)
        newButton.text = "${Lang.bundle.getString("button.new.name")}\t${Lang.bundle.getString("button.new.accelerator")}"
        newButton.accelerator = SWT.MOD1 or 'N'.toInt()
        newButton.addListener(SWT.Selection) {
            CommonCommands.new()
        }
    }
}