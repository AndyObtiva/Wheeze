package com.deflatedpickle.wheeze.util

import com.deflatedpickle.wheeze.brush.Brush
import org.netrexx.process.NetRexxC
import java.io.File
import java.nio.file.Paths

object BrushUtil {
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

        for (i in File(path).walkTopDown().drop(1)) {
            if (i.readText().contains("extends Brush")) {
                fileArray.add(i.absolutePath)
            }
        }

        // TODO: Move the files to out/ with a CLI arg
        NetRexxC.main2(fileArray.toTypedArray())

        cleanBrushes()
    }

    /**
     * Moves the brushes into out/.
     */
    private fun cleanBrushes() {
        val currentPath = Paths.get("").toAbsolutePath().toString()
        for (i in File(currentPath).walkTopDown()) {
            if (!i.absolutePath.contains("out") && i.name.endsWith("Brush.class")) {
                i.copyTo(
                    File(currentPath + "\\out\\production\\classes\\com\\deflatedpickle\\wheeze\\brush\\custom\\" + i.name),
                    true
                )
                i.delete()
            }
        }
    }
}