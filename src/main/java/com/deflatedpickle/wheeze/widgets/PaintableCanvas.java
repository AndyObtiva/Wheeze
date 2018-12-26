package com.deflatedpickle.wheeze.widgets;

import com.deflatedpickle.wheeze.brush.Brush;
import com.deflatedpickle.wheeze.util.BrushUtil;
import com.deflatedpickle.wheeze.util.ToolType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import java.util.Objects;

public class PaintableCanvas extends Canvas {
    public GC paintGC = new GC(this);
    private Canvas canvas = this;

    private ToolType activeToolType = ToolType.BRUSH;

    private PaintListener paintListener = e -> e.gc.setAntialias(SWT.ON);

    private boolean doDraw = false;
    private boolean doBackground = true;

    public PaintableCanvas(Composite parent, int style) {
        super(parent, style);

        GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
        gridData.widthHint = 340;
        gridData.heightHint = 340;
        this.setLayoutData(gridData);

        // this.setLayoutData(new GridData(GridData.FILL_BOTH));

        this.setFocus();

        this.addPaintListener(paintListener);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                doDraw = true;
                canvas.setFocus();
                doPaint();
            }

            @Override
            public void mouseUp(MouseEvent e) {
                doDraw = false;
            }
        });

        this.addMouseMoveListener(e -> {
            if (doDraw && Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.mode == Brush.Mode.CONTINUOUS) {
                canvas.setFocus();
                doPaint();
            }
        });

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (doBackground) {
                    drawBackground(/* 340, 340 */);
                    doBackground = false;
                }
            }
        });
    }

    public void setActiveToolType(ToolType newValue) {
        activeToolType = newValue;
    }

    private void doPaint() {
        int color;

        switch (activeToolType) {
            case BRUSH:
                color = SWT.COLOR_BLACK;
                break;

            case ERASER:
            case NONE:
            default:
                color = SWT.COLOR_TRANSPARENT;
                break;
        }

        paintListener.paintControl(new PaintEvent(new Event() {
            {
                this.widget = canvas;
                this.gc = paintGC;

                this.gc.setBackground(Display.getDefault().getSystemColor(color));

                Point cursorLocation;
                if (Display.getDefault().getFocusControl() != null) {
                    cursorLocation = Display.getDefault().getFocusControl().toControl(Display.getDefault().getCursorLocation());
                }
                else {
                    cursorLocation = new Point(0, 0);
                }

                Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).paintScript(paintGC, cursorLocation);
            }
        }));
    }

    private void drawBackground(/* int width, int height */) {
        Rectangle displayRect = this.getClientArea();

        // this.paintGC.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
        // this.paintGC.drawRectangle(0, 0, displayRect.width, displayRect.height);
        // this.paintGC.fillRectangle(0, 0, displayRect.width, displayRect.height);

        this.paintGC.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        // this.paintGC.drawRectangle((displayRect.width / 2) - (width / 2), (displayRect.height / 2) - (height / 2), width, height);
        // this.paintGC.fillRectangle((displayRect.width / 2) - (width / 2), (displayRect.height / 2) - (height / 2), width, height);
        this.paintGC.drawRectangle(0, 0, displayRect.width, displayRect.height);
        this.paintGC.fillRectangle(0, 0, displayRect.width, displayRect.height);
    }
}
