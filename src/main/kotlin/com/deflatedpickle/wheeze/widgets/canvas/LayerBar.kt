package com.deflatedpickle.wheeze.widgets.canvas

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class LayerBar(parent: Composite) : Composite(parent, SWT.NONE) {
    val toolBar = ToolBar(this, SWT.HORIZONTAL or SWT.WRAP or SWT.RIGHT)

    val hideButton = ToolItem(toolBar, SWT.CHECK)

    // val previewCanvas = Canvas(this, SWT.NONE)

    val mainButton = ToolItem(toolBar, SWT.PUSH)

    val deleteButton = ToolItem(toolBar, SWT.CHECK)

    init {
        hideButton.text = "#"
        deleteButton.text = "X"

        toolBar.pack()
    }
}