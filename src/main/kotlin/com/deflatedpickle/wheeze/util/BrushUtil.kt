package com.deflatedpickle.wheeze.util

import com.deflatedpickle.wheeze.brush.Brush
import org.netrexx.process.NetRexxA
import java.io.File
import java.lang.Class

object BrushUtil {
    val netRexx = NetRexxA()
    /**
     * The list of registered brushes.
     */
    val brushList = mutableListOf<Brush>()
    /**
     * The brush currently selected.
     */
    var activeBrush: Brush? = null
    // /**
    //  * The list of registered erasers.
    //  */
    // val eraserList = mutableListOf<Brush>()
    // /**
    //  * The eraser currently selected.
    //  */
    // val activeEraser: Brush? = null

    /**
     * Loads all the brushes in a given path.
     *
     * @param path The path to load brushes from.
     */
    fun loadBrushes(path: String) {
        val fileArray = mutableListOf<String>()
        val nameArray = mutableListOf<String>()

        for (i in File(path).walkTopDown().drop(1)) {
            if (i.readText().contains("extends Brush")) {
                fileArray.add(i.absolutePath)
                nameArray.add(i.nameWithoutExtension)
            }
        }

        // NetRexxC.main2(fileArray.toTypedArray())

        // Pass the brush files followed by the command line arguments
        this.netRexx.parse(fileArray.toTypedArray(), arrayOf())

        for (i in nameArray) {
            val clazz: Class<Brush> = this.netRexx.getClassObject("com.deflatedpickle.wheeze.brush.custom", i) as Class<Brush>
            this.brushList.add(clazz.newInstance())
        }
    }
}