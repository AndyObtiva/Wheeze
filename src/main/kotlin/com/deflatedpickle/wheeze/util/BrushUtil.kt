package com.deflatedpickle.wheeze.util

import org.netrexx.process.NetRexxC
import java.io.File
import java.nio.file.Paths

object BrushUtil {
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