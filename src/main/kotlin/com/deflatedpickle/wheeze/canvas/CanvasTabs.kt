package com.deflatedpickle.wheeze.canvas

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.TabFolder
import org.eclipse.swt.widgets.TabItem

class CanvasTabs(parent: Composite) : Composite(parent, SWT.NONE) {
    private val tabFolder = TabFolder(this, SWT.TOP)

    fun newCanvas(name: String) {
        val tabItem = TabItem(tabFolder, SWT.NULL)
        tabItem.text = name

        val canvasHolder = CanvasHolder(tabFolder)
        canvasHolder.layout = FillLayout()
        canvasHolder.canvas.setFocus()

        tabItem.control = canvasHolder
    }
}