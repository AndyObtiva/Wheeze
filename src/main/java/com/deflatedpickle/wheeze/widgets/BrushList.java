package com.deflatedpickle.wheeze.widgets;

import com.deflatedpickle.wheeze.brush.Brush;
import com.deflatedpickle.wheeze.util.BrushUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class BrushList extends Composite {

    public BrushList(Composite parent, int style) {
        super(parent, style);
        this.setLayout(new FillLayout());

        List list = new List(this, SWT.SINGLE);

        for (Brush brush : BrushUtil.INSTANCE.getBrushList()) {
            list.add(brush.name);
        }

        list.addListener(SWT.Selection, event -> BrushUtil.INSTANCE.setActiveBrush(BrushUtil.INSTANCE.getBrushList().get(list.getSelectionIndex())));

        this.pack();
    }
}
