package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.util.BrushUtil
import org.jruby.embed.LocalVariableBehavior
import org.jruby.embed.PathType
import org.jruby.embed.ScriptingContainer


fun main(args: Array<String>) {
    val resourcePath = "".javaClass::class.java.getResource("/brushes/").path
    BrushUtil.loadBrushes(resourcePath)

    BrushUtil.activeBrush = BrushUtil.brushList[0]

    // Run the window script
    val ruby = ScriptingContainer(LocalVariableBehavior.PERSISTENT)
    ruby.runScriptlet(PathType.RELATIVE, "src/main/ruby/com/deflatedpickle/wheeze/window.rb")
}