package com.deflatedpickle.wheeze.widgets.toolbars.hotbar

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.ToolBar
import org.eclipse.swt.widgets.ToolItem

class EditBar(parent: Composite) : Composite(parent, SWT.NONE) {
    val toolBar = ToolBar(this, SWT.HORIZONTAL or SWT.WRAP or SWT.RIGHT)

    init {
        val undoButton = ToolItem(toolBar, SWT.PUSH)
        undoButton.text = "Undo"

        val redoButton = ToolItem(toolBar, SWT.PUSH)
        redoButton.text = "Redo"

        toolBar.pack()
    }
}