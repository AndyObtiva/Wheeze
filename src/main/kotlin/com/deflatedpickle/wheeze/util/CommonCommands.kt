package com.deflatedpickle.wheeze.util

import com.deflatedpickle.wheeze.widgets.Widgets

object CommonCommands {
    fun new() {
        CommonVariables.createdFiles++
        Widgets.canvasTabs.newCanvas("untitled ${CommonVariables.createdFiles}")
    }
}