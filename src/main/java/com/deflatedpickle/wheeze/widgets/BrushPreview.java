package com.deflatedpickle.wheeze.widgets;

import com.deflatedpickle.wheeze.brush.Brush;
import com.deflatedpickle.wheeze.brush.BrushProperties;
import com.deflatedpickle.wheeze.util.BrushUtil;
import com.deflatedpickle.wheeze.util.RangedInteger;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import java.util.ArrayList;
import java.util.Objects;

public class BrushPreview extends Canvas {
    private GC paintGC = new GC(this);
    private Canvas canvas = this;

    private ArrayList<Pair<Float, Float>> bezierPoints = new ArrayList<>();

    private PaintListener paintListener = e -> e.gc.setAntialias(SWT.ON);

    public BrushPreview(Composite parent, int style) {
        super(parent, style);

        this.addPaintListener(paintListener);
    }

    public void redrawBrush() {
        bezierPoints.clear();

        BrushProperties brushProperties = Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties;

        Rectangle displayRect = canvas.getClientArea();

        Float pointX = (brushProperties.width.getCurrent() / 10) / 2f;
        Float pointY = displayRect.height / 2f - (brushProperties.height.getCurrent() / 10) / 2;

        ImmutablePair<Float, Float> startPoint = new ImmutablePair<>(10f - pointX, pointY);
        ImmutablePair<Float, Float> endPoint = new ImmutablePair<>(displayRect.width - 20f, pointY);
        ImmutablePair<Float, Float> leftControl = new ImmutablePair<>(60f, pointY - 40f);
        ImmutablePair<Float, Float> rightControl = new ImmutablePair<>(displayRect.width - pointX - 60f, pointY + 40f);

        for (float t = 0.0f; t < 1; t += 0.01f) {
            bezierPoints.add(new ImmutablePair<>(
                    workOutPoint(startPoint.left, leftControl.left, rightControl.left, endPoint.left, t),
                    workOutPoint(startPoint.right, leftControl.right, rightControl.right, endPoint.right, t)
            ));
        }
        bezierPoints.add(new ImmutablePair<>(
                workOutPoint(startPoint.left, leftControl.left, rightControl.left, endPoint.left, 1f),
                workOutPoint(startPoint.right, leftControl.right, rightControl.right, endPoint.right, 1f)
        ));

        paintListener.paintControl(new PaintEvent(new Event() {
            {
                this.widget = canvas;
                this.gc = paintGC;

                this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

                this.gc.drawRectangle(0, 0, displayRect.width, displayRect.height);
                this.gc.fillRectangle(0, 0, displayRect.width, displayRect.height);

                this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));

                BrushProperties brushProperties = SerializationUtils.clone(Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties);
                brushProperties.width.setCurrent(brushProperties.width.getCurrent() / 10);
                brushProperties.height.setCurrent(brushProperties.height.getCurrent() / 10);

                for (Pair<Float, Float> pair : bezierPoints) {
                    BrushUtil.INSTANCE.getActiveBrush().brushScript.paint(paintGC,
                            new Point(pair.getLeft().intValue(), pair.getRight().intValue()),
                            brushProperties);
                }

                // Debug Points:

                // Start Point
                // this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
                // this.gc.drawOval(startPoint.left.intValue(), startPoint.right.intValue(), 6, 6);
                // this.gc.fillOval(startPoint.left.intValue(), startPoint.right.intValue(), 6, 6);

                // End Point
                // this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA));
                // this.gc.drawOval(endPoint.left.intValue(), endPoint.right.intValue(), 6, 6);
                // this.gc.fillOval(endPoint.left.intValue(), endPoint.right.intValue(), 6, 6);

                // Left Control
                // this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
                // this.gc.drawOval(leftControl.left.intValue(), leftControl.right.intValue(), 6, 6);
                // this.gc.fillOval(leftControl.left.intValue(), leftControl.right.intValue(), 6, 6);

                // Right Control
                // this.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
                // this.gc.drawOval(rightControl.left.intValue(), rightControl.right.intValue(), 6, 6);
                // this.gc.fillOval(rightControl.left.intValue(), rightControl.right.intValue(), 6, 6);
            }
        }));
    }

    // Credit: http://csharphelper.com/blog/2014/12/draw-a-bezier-curve-by-hand-in-c/
    private float workOutPoint(float start,
                               float end,
                               float leftControl,
                               float rightControl,
                               float iteration) {
        return (float) (start * Math.pow((1 - iteration), 3) +
                end * 3 * iteration * Math.pow((1 - iteration), 2) +
                leftControl * 3 * Math.pow(iteration, 2) * (1 - iteration) +
                rightControl * Math.pow(iteration, 3));
    }
}
