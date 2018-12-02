package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.brush.Brush
import com.deflatedpickle.wheeze.util.BrushUtil
import com.google.common.reflect.ClassPath
import org.jruby.embed.LocalVariableBehavior
import org.jruby.embed.PathType
import org.jruby.embed.ScriptingContainer


fun main(args: Array<String>) {
    val resourcePath = "".javaClass::class.java.getResource("/brushes/").path
    BrushUtil.loadBrushes(resourcePath)

    // Find all the brushes loaded into the package.
    val brushClasses = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses("com.deflatedpickle.wheeze.brush.custom")

    // Print the names, just to check they exist.
    for (i in brushClasses) {
        val clazz = ClassLoader.getSystemClassLoader().loadClass(i.name)

        // Make an instance of each brush
        val instance: Brush = clazz.newInstance() as Brush
        // Add the instance to the brush list
        BrushUtil.brushList.add(instance)
    }

    BrushUtil.activeBrush = BrushUtil.brushList[0]

    // Run the window script
    val ruby = ScriptingContainer(LocalVariableBehavior.PERSISTENT)
    ruby.runScriptlet(PathType.RELATIVE, "src/main/ruby/com/deflatedpickle/wheeze/window.rb")
}