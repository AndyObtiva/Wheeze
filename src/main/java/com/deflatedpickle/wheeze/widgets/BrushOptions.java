package com.deflatedpickle.wheeze.widgets;

import com.deflatedpickle.wheeze.brush.Brush;
import com.deflatedpickle.wheeze.brush.BrushProperties;
import com.deflatedpickle.wheeze.util.BrushUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.Objects;

public class BrushOptions extends Composite {
    private Label name = new Label(this, SWT.BORDER);

    private Label modeLabel = new Label(this, SWT.NONE);
    private Combo mode = new Combo(this, SWT.BORDER | SWT.READ_ONLY);

    private Label sizeLabel = new Label(this, SWT.NONE);
    private Scale size = new Scale(this, SWT.NONE);
    private Label widthLabel = new Label(this, SWT.NONE);
    private Scale width = new Scale(this, SWT.NONE);
    private Label heightLabel = new Label(this, SWT.NONE);
    private Scale height = new Scale(this, SWT.NONE);

    // private Label opacityLabel = new Label(this, SWT.NONE);
    // private Scale opacity = new Scale(this, SWT.NONE);
    // private Label densityLabel = new Label(this, SWT.NONE);
    // private Scale density = new Scale(this, SWT.NONE);

    // private Button widthPressure = new Button(this, SWT.CHECK);
    // private Button heightPressure = new Button(this, SWT.CHECK);
    // private Button opacityPressure = new Button(this, SWT.CHECK);
    // private Button densityPressure = new Button(this, SWT.CHECK);

    public BrushOptions(Composite parent, int style) {
        super(parent, style);

        this.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.setLayout(new GridLayout(2, false));

        GridData twoWide = new GridData();
        twoWide.horizontalSpan = 2;
        twoWide.grabExcessHorizontalSpace = true;
        twoWide.horizontalAlignment = SWT.CENTER;
        twoWide.widthHint = this.getParent().getClientArea().width - 40;

        name.setLayoutData(twoWide);

        mode.add("Single");
        mode.add("Continuous");

        modeLabel.setText("Mode");

        GridData scaleData = new GridData();
        scaleData.widthHint = 120;

        sizeLabel.setText("Size");
        size.setLayoutData(scaleData);
        size.setIncrement(1);

        widthLabel.setText("Width");
        width.setLayoutData(scaleData);
        width.setIncrement(1);

        heightLabel.setText("Height");
        height.setLayoutData(scaleData);
        height.setIncrement(1);

        // opacityLabel.setText("Opacity");
        // opacity.setLayoutData(scaleData);
        // opacity.setMinimum(0);
        // opacity.setMaximum(100);
        // opacity.setIncrement(1);

        // densityLabel.setText("Density");
        // density.setLayoutData(scaleData);
        // density.setMinimum(0);
        // density.setMaximum(100);
        // density.setIncrement(1);

        // widthPressure.setText("Width Pressure");
        // widthPressure.setLayoutData(twoWide);
        // heightPressure.setText("Height Pressure");
        // heightPressure.setLayoutData(twoWide);
        // opacityPressure.setText("Opacity Pressure");
        // opacityPressure.setLayoutData(twoWide);
        // densityPressure.setText("Density Pressure");
        // densityPressure.setLayoutData(twoWide);

        // Listeners

        mode.addListener(SWT.Selection, event -> {
            switch (mode.getSelectionIndex()) {
                case 0:
                    Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.mode = Brush.Mode.SINGLE;
                    break;

                case 1:
                    Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.mode = Brush.Mode.CONTINUOUS;
                    break;
            }
        });

        size.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(size.getSelection());
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(size.getSelection());
            width.setSelection(size.getSelection());
            height.setSelection(size.getSelection());
        });
        width.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(width.getSelection()));
        height.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(height.getSelection()));

        // opacity.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.opacity = opacity.getSelection());
        // density.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.density = density.getSelection());

        // widthPressure.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.widthPressure = widthPressure.getSelection());
        // heightPressure.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.heightPressure = heightPressure.getSelection());
        // opacityPressure.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.opacityPressure = opacityPressure.getSelection());
        // densityPressure.addListener(SWT.Selection, event -> Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.densityPressure = densityPressure.getSelection());

        this.pack();
    }

    public void updateControls() {
        BrushProperties brushProperties = Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties;

        name.setText(brushProperties.name);

        switch (brushProperties.mode) {
            case SINGLE:
                mode.select(0);
                break;

            case CONTINUOUS:
                mode.select(1);
                break;
        }

        width.setSelection(brushProperties.width.getCurrent());
        width.setMinimum(brushProperties.width.getMin());
        width.setMaximum(brushProperties.width.getMax());

        height.setSelection(brushProperties.height.getCurrent());
        height.setMinimum(brushProperties.height.getMin());
        height.setMaximum(brushProperties.height.getMax());

        // opacity.setSelection(brushProperties.opacity);

        // density.setSelection(brushProperties.density);

        // widthPressure.setSelection(brushProperties.widthPressure);
        // heightPressure.setSelection(brushProperties.heightPressure);
        // opacityPressure.setSelection(brushProperties.opacityPressure);
        // densityPressure.setSelection(brushProperties.densityPressure);
    }
}
