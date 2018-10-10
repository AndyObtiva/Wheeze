package com.deflatedpickle.wheeze.widgets.toolbars.hotbar

import com.deflatedpickle.wheeze.util.CommonCommands
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.ToolBar
import org.eclipse.swt.widgets.ToolItem

class FileBar(parent: Composite) : Composite(parent, SWT.NONE) {
    val toolBar = ToolBar(this, SWT.HORIZONTAL or SWT.WRAP or SWT.RIGHT or SWT.FLAT)

    init {
        val newButton = ToolItem(toolBar, SWT.PUSH)
        newButton.text = "New"
        newButton.addListener(SWT.Selection) {
            CommonCommands.new()
        }

        // val openButton = ToolItem(toolBar, SWT.PUSH)
        // openButton.text = "Open"

        // ToolItem(toolBar, SWT.SEPARATOR)

        // val saveButton = ToolItem(toolBar, SWT.PUSH)
        // saveButton.text = "Save"

        toolBar.pack()
    }
}