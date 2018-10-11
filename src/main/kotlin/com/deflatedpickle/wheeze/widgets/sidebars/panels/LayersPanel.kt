package com.deflatedpickle.wheeze.widgets.sidebars.panels

import com.deflatedpickle.wheeze.util.CommonVariables
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class LayersPanel(parent: Composite) : Composite(parent, SWT.NONE) {
    val group = Group(this, SWT.NONE)

    val newButton = Button(group, SWT.PUSH)

    val tree = Tree(group, SWT.BORDER or SWT.FULL_SELECTION or SWT.MULTI)

    init {
        group.layout = GridLayout()
        group.text = "Layers"

        newButton.text = "+"
        newButton.addListener(SWT.Selection) {
            if (CommonVariables.currentCanvas != null) {
                addLayer("Untitled")
            }
        }

        // tree.headerVisible = true

        val layerColumn = TreeColumn(tree, SWT.NONE)
        // layerColumn.text = "Layer"
        layerColumn.width = 100

        tree.addListener(SWT.Selection) {
            CommonVariables.currentCanvas!!.canvas.canvas.selectedLayer = tree.items.indexOf(tree.selection[0])
        }

        group.pack()
    }

    fun addLayer(name: String) {
        CommonVariables.currentCanvas!!.canvas.canvas.addLayer()

        val item = TreeItem(tree, SWT.NONE)
        item.setText(arrayOf(name))

        tree.deselectAll()
        tree.select(item)
    }
}