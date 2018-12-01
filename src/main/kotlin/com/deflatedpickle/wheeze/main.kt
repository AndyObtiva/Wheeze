package com.deflatedpickle.wheeze

import com.deflatedpickle.wheeze.util.BrushUtil
import com.google.common.reflect.ClassPath


fun main(args: Array<String>) {
    val resourcePath = "".javaClass::class.java.getResource("/brushes/").path
    BrushUtil.loadBrushes(resourcePath)

    // Find all the brushes loaded into the package.
    val brushClasses = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses("com.deflatedpickle.wheeze.brush.custom")

    // Print the names, just to check they exist.
    for (i in brushClasses) {
        val clazz = ClassLoader.getSystemClassLoader().loadClass(i.name)

        val field = clazz.getField("name")
        val instance = clazz.newInstance()

        clazz.getMethod("paint", Float::class.java).invoke(instance, 0f)

        println(field.get(instance))
    }
}