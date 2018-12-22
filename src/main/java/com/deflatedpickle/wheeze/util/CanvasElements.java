package com.deflatedpickle.wheeze.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import java.util.Objects;

/**
 * A utility singleton to quickly provide elements needed for the JRuby GUI.
 */
// TODO: Convert to Ruby
public class CanvasElements {
    private static CanvasElements ourInstance = new CanvasElements();
    public static CanvasElements getInstance() {
        return ourInstance;
    }

    private ToolType activeToolType = ToolType.BRUSH;

    public void setActiveToolType(ToolType newValue) {
        activeToolType = newValue;
    }

    public PaintListener paintListener = e -> e.gc.setAntialias(SWT.ON);

    @SuppressWarnings("WeakerAccess")
    public GC paintGC;

    public void prepareGraphics(Canvas canvas) {
        this.paintGC = new GC(canvas);
    }

    public void drawBackground(Canvas canvas) {
        this.paintGC.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

        Rectangle displayRect = canvas.getDisplay().getClientArea();

        this.paintGC.drawRectangle(0, 0, displayRect.width, displayRect.height);
        this.paintGC.fillRectangle(0, 0, displayRect.width, displayRect.height);
    }

    public void doPaint(Canvas canvas, Point cursorLocation) {
        int color;

        switch (activeToolType) {
            case BRUSH:
                color = SWT.COLOR_BLACK;
                break;

            case ERASER:
            case NONE:
            default:
                color = SWT.COLOR_WHITE;
                break;
        }

        paintListener.paintControl(new PaintEvent(new Event() {
            {
                this.widget = canvas;
                this.gc = paintGC;

                this.gc.setBackground(Display.getDefault().getSystemColor(color));

                Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).paint(this.gc, cursorLocation);
            }
        }));
    }
}
