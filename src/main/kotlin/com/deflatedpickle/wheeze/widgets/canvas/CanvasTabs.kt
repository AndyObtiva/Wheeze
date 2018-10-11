package com.deflatedpickle.wheeze.widgets.canvas

import com.deflatedpickle.wheeze.util.CommonVariables
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.TabFolder
import org.eclipse.swt.widgets.TabItem
import org.eclipse.swt.widgets.Widget

class CanvasTabs(parent: Composite) : Composite(parent, SWT.NONE) {
    val tabFolder = TabFolder(this, SWT.TOP)

    init {
        tabFolder.addListener(SWT.Selection) {
            if (tabFolder.selection[0].control != null) {
                CommonVariables.currentCanvas = (tabFolder.selection[0].control as CanvasHolder)
            }
        }
    }

    fun newCanvas(name: String): List<Widget> {
        val tabItem = TabItem(tabFolder, SWT.NULL)
        tabItem.text = name

        val canvasHolder = CanvasHolder(tabFolder)
        canvasHolder.layout = FillLayout()
        canvasHolder.canvas.setFocus()

        tabItem.control = canvasHolder

        return listOf(tabItem, canvasHolder)
    }
}