package com.deflatedpickle.wheeze.widgets;

import com.deflatedpickle.wheeze.brush.Brush;
import com.deflatedpickle.wheeze.util.BrushUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class BrushList extends Composite {

    public BrushList(Composite parent, int style) {
        super(parent, style);
        this.setLayout(new FillLayout());

        this.setLayoutData(new GridData(GridData.FILL_BOTH));

        List list = new List(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

        for (Brush brush : BrushUtil.INSTANCE.getBrushList()) {
            list.add(brush.brushProperties.name);
        }

        list.addListener(SWT.Selection, event -> BrushUtil.INSTANCE.setActiveBrush(BrushUtil.INSTANCE.getBrushList().get(list.getSelectionIndex())));
        list.select(0);

        BrushUtil.INSTANCE.setActiveBrush(BrushUtil.INSTANCE.getBrushList().get(0));

        this.pack();
        this.getParent().layout();
    }
}
