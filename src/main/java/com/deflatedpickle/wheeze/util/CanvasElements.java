package com.deflatedpickle.wheeze.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import java.util.Objects;

public class CanvasElements {
    private static CanvasElements ourInstance = new CanvasElements();
    public static CanvasElements getInstance() {
        return ourInstance;
    }

    public PaintListener paintListener = e -> e.gc.setAntialias(SWT.ON);

    public GC paintGC;

    public void doPaint(Canvas canvas, Point cursorLocation) {
        if (paintGC == null) {
            paintGC = new GC(canvas);
        }

        paintListener.paintControl(new PaintEvent(new Event() {
            {
                this.widget = canvas;
                this.gc = paintGC;

                this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));

                Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).paint(gc, cursorLocation);
            }
        }));
    }
}
