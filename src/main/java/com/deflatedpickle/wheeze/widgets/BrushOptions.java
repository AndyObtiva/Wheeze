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

    public BrushPreview brushPreview = new BrushPreview(this, SWT.BORDER);

    private Label modeLabel = new Label(this, SWT.NONE);
    private Combo mode = new Combo(this, SWT.BORDER | SWT.READ_ONLY);

    private SizeScale size = new SizeScale(this, SWT.NONE, "Size");
    private SizeScale width = new SizeScale(this, SWT.NONE, "Width");
    private SizeScale height = new SizeScale(this, SWT.NONE, "Height");

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
        twoWide.horizontalAlignment = SWT.CENTER;
        twoWide.widthHint = this.getParent().getClientArea().width - 40;

        name.setLayoutData(twoWide);

        GridData previewData = new GridData();
        previewData.horizontalSpan = 2;
        previewData.horizontalAlignment = SWT.CENTER;
        previewData.widthHint = this.getParent().getClientArea().width - 20;
        brushPreview.setLayoutData(previewData);

        mode.add("Single");
        mode.add("Continuous");

        modeLabel.setText("Mode");

        GridData scaleData = new GridData();
        scaleData.horizontalSpan = 2;

        // sizeLabel.setText("Size");
        // size.setLayoutData(scaleData);
        // size.setIncrement(1);

        size.setLayoutData(scaleData);
        width.setLayoutData(scaleData);
        height.setLayoutData(scaleData);

        // widthLabel.setText("Width");
        // width.setLayoutData(scaleData);
        // width.setIncrement(1);

        // heightLabel.setText("Height");
        // height.setLayoutData(scaleData);
        // height.setIncrement(1);

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

        // FIXME: It works, but this whole mess could be done better
        size.scale.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(size.scale.getSelection());
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(size.scale.getSelection());
            width.scale.setSelection(size.scale.getSelection());
            height.scale.setSelection(size.scale.getSelection());
            width.spinner.setSelection(size.scale.getSelection());
            height.spinner.setSelection(size.scale.getSelection());
            brushPreview.redrawBrush();
        });
        size.spinner.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(size.spinner.getSelection());
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(size.spinner.getSelection());
            width.scale.setSelection(size.spinner.getSelection());
            height.scale.setSelection(size.spinner.getSelection());
            width.spinner.setSelection(size.spinner.getSelection());
            height.spinner.setSelection(size.spinner.getSelection());
            brushPreview.redrawBrush();
        });
        width.scale.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(width.scale.getSelection());
            brushPreview.redrawBrush();
        });
        width.spinner.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.width.setCurrent(width.spinner.getSelection());
            brushPreview.redrawBrush();
        });
        height.scale.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(height.scale.getSelection());
            brushPreview.redrawBrush();
        });
        height.spinner.addListener(SWT.Selection, event -> {
            Objects.requireNonNull(BrushUtil.INSTANCE.getActiveBrush()).brushProperties.height.setCurrent(height.spinner.getSelection());
            brushPreview.redrawBrush();
        });

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

        brushPreview.redrawBrush();

        switch (brushProperties.mode) {
            case SINGLE:
                mode.select(0);
                break;

            case CONTINUOUS:
                mode.select(1);
                break;
        }

        // width.setSelection(brushProperties.width.getCurrent());
        // width.setMinimum(brushProperties.width.getMin());
        // width.setMaximum(brushProperties.width.getMax());

        // height.setSelection(brushProperties.height.getCurrent());
        // height.setMinimum(brushProperties.height.getMin());
        // height.setMaximum(brushProperties.height.getMax());

        width.updateValues(brushProperties.width.getMin(),
                brushProperties.width.getMax(),
                brushProperties.width.getCurrent(),
                1);

        height.updateValues(brushProperties.height.getMin(),
                brushProperties.height.getMax(),
                brushProperties.height.getCurrent(),
                1);

        // opacity.setSelection(brushProperties.opacity);

        // density.setSelection(brushProperties.density);

        // widthPressure.setSelection(brushProperties.widthPressure);
        // heightPressure.setSelection(brushProperties.heightPressure);
        // opacityPressure.setSelection(brushProperties.opacityPressure);
        // densityPressure.setSelection(brushProperties.densityPressure);
    }
}
