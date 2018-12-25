package com.deflatedpickle.wheeze.util;

import com.deflatedpickle.wheeze.widgets.BrushOptions;
import com.deflatedpickle.wheeze.widgets.PaintableCanvas;
import org.eclipse.swt.widgets.Shell;

public class CompatibilityUtil {
    private static CompatibilityUtil ourInstance = new CompatibilityUtil();

    public static CompatibilityUtil getInstance() {
        return ourInstance;
    }

    public Shell shell;
    public PaintableCanvas paintableCanvas;
    public BrushOptions brushOptions;

    private CompatibilityUtil() {
    }
}
