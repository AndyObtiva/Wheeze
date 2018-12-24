package com.deflatedpickle.wheeze.util;

import org.eclipse.swt.widgets.Shell;

public class CompatibilityUtil {
    private static CompatibilityUtil ourInstance = new CompatibilityUtil();

    public static CompatibilityUtil getInstance() {
        return ourInstance;
    }

    public Shell shell;

    private CompatibilityUtil() {
    }
}
