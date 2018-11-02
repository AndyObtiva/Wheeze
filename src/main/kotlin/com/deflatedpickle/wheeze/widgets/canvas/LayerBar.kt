package com.deflatedpickle.wheeze.widgets.canvas

import com.deflatedpickle.wheeze.widgets.canvas.layers.Layer
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class LayerBar(parent: Composite, treeItem: TreeItem, layerInstance: Layer) : Composite(parent, SWT.NONE) {
    val toolBar = ToolBar(this, SWT.HORIZONTAL or SWT.WRAP or SWT.RIGHT)

    val hideButton = ToolItem(toolBar, SWT.CHECK)
    val lockButton = ToolItem(toolBar, SWT.CHECK)

    // val previewCanvas = Canvas(this, SWT.NONE)

    val mainButton = ToolItem(toolBar, SWT.PUSH)

    val deleteButton = ToolItem(toolBar, SWT.CHECK)

    init {
        this.layoutData = GridLayout()

        hideButton.text = "Hide"
        hideButton.addListener(SWT.Selection) {
            layerInstance.isHidden = true
        }

        lockButton.text = "Lock"
        deleteButton.text = "Delete"

        mainButton.addListener(SWT.Selection) {
            (parent as Tree).deselectAll()
            parent.select(treeItem)
        }

        toolBar.pack()
    }
}