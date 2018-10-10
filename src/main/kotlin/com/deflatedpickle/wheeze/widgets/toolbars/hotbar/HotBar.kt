package com.deflatedpickle.wheeze.widgets.toolbars.hotbar

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.CoolBar
import org.eclipse.swt.widgets.CoolItem

class HotBar(parent: Composite) : Composite(parent, SWT.NONE) {
    private val coolBar = CoolBar(this, SWT.HORIZONTAL) // or SWT.FLAT)

    val fileBar = FileBar(coolBar)
    // val editBar = EditBar(coolBar)

    val barList = listOf(fileBar) // , editBar)

    init {
        for (i in barList) {
            val coolItem = CoolItem(coolBar, SWT.DROP_DOWN)
            val itemSize = i.computeSize(SWT.DEFAULT, SWT.DEFAULT)
            coolItem.preferredSize = coolItem.computeSize(itemSize.x, itemSize.y)
            coolItem.control = i
        }

        coolBar.addListener(SWT.Resize) {
            this.parent.layout()
        }
    }
}