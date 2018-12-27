package com.deflatedpickle.wheeze.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;

class SizeScale extends Composite {
    private Label label = new Label(this, SWT.NONE);
    Scale scale = new Scale(this, SWT.NONE);
    Spinner spinner = new Spinner(this, SWT.BORDER);

    public SizeScale(Composite parent, int style, String text) {
        super(parent, style);

        this.setLayout(new GridLayout(3, false));

        GridData labelData = new GridData();
        labelData.widthHint = 40;
        label.setText(text);
        label.setLayoutData(labelData);

        GridData scaleData = new GridData();
        scaleData.widthHint = 80;

        scale.setLayoutData(scaleData);

        scale.addListener(SWT.Selection, e -> spinner.setSelection(scale.getSelection()));
        spinner.addListener(SWT.Selection, e -> scale.setSelection(spinner.getSelection()));

        this.pack();
    }

    public void updateValues(Integer valueMin, Integer valueMax, Integer valueCurrent, Integer valueIncrement) {
        scale.setMinimum(valueMin);
        scale.setMaximum(valueMax);
        scale.setSelection(valueCurrent);
        scale.setIncrement(valueIncrement);

        spinner.setMinimum(valueMin);
        spinner.setMaximum(valueMax);
        spinner.setSelection(valueCurrent);
        spinner.setIncrement(valueIncrement);
    }
}
