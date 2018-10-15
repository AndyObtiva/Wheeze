package com.deflatedpickle.wheeze.widgets.sidebars.panels

import com.deflatedpickle.wheeze.util.CommonVariables
import com.deflatedpickle.wheeze.widgets.canvas.LayerBar
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.TreeEditor
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.layout.RowLayout
import org.eclipse.swt.widgets.*

class LayersPanel(parent: Composite) : Composite(parent, SWT.NONE) {
    val group = Group(this, SWT.NONE)

    val newButton = Button(group, SWT.PUSH)

    val tree = Tree(group, SWT.BORDER or SWT.FULL_SELECTION or SWT.MULTI)

    init {
        val buttonLayout = GridData(GridData.FILL_HORIZONTAL)

        val gridData = GridData(GridData.FILL_BOTH)

        group.layout = GridLayout()
        group.layoutData = gridData
        group.text = "Layers"

        tree.layoutData = gridData

        newButton.text = "+"
        newButton.addListener(SWT.Selection) {
            if (CommonVariables.currentCanvas != null) {
                addLayer("Untitled")
            }
        }
        newButton.layoutData = buttonLayout

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
        // item.setText(arrayOf(name))

        // FIXME: The widget bar does not span the width of the tree item
        val widgetBar = LayerBar(tree)
        widgetBar.layout = GridLayout()
        widgetBar.layoutData = GridData(GridData.FILL_HORIZONTAL)
        widgetBar.mainButton.text = name

        val treeEditor = TreeEditor(tree)
        treeEditor.horizontalAlignment = SWT.LEFT
        treeEditor.verticalAlignment = SWT.TOP
        treeEditor.grabHorizontal = true

        treeEditor.setEditor(widgetBar, item)

        tree.deselectAll()
        tree.select(item)
    }
}