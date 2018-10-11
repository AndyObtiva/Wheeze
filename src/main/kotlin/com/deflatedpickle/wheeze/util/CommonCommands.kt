package com.deflatedpickle.wheeze.util

import com.deflatedpickle.wheeze.widgets.Widgets
import com.deflatedpickle.wheeze.widgets.canvas.CanvasHolder
import org.eclipse.swt.widgets.TabItem

object CommonCommands {
    fun new() {
        CommonVariables.createdFiles++
        val returnVars = Widgets.canvasTabs.newCanvas("untitled ${CommonVariables.createdFiles}")
        val canvas = (returnVars[1] as CanvasHolder)
        val tab = returnVars[0] as TabItem
        Widgets.canvasTabs.tabFolder.setSelection(tab)

        CommonVariables.currentCanvas = canvas

        Widgets.sideBar.layersPane.addLayer("Background")
    }
}