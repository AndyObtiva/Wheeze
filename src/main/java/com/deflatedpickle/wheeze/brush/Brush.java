package com.deflatedpickle.wheeze.brush;

import com.deflatedpickle.wheeze.util.CompatibilityUtil;
import com.deflatedpickle.wheeze.util.RangedInteger;
import org.eclipse.swt.graphics.Point;

/**
 * Defines a brush to be used by the canvas.
 */
public class Brush {
    public enum Mode {
        /**
         * Draws the brush pattern only when the mouse is clicked.
         */
        SINGLE,
        /**
         * Draws the brush pattern when the mouse is held down and dragged.
         */
        CONTINUOUS
    }

    public BrushProperties brushProperties;

    /**
     * The brush script to draw with.
     */
    public BrushScript brushScript;

    /**
     * Constructs a new brush with the default values.
     */
    public Brush() {
        this.brushProperties = new BrushProperties(
                "Untitled Brush",
                Mode.CONTINUOUS,
                new RangedInteger(1, 10, 100),
                new RangedInteger(1, 10, 100),
                100,
                100,
                false,
                false,
                false,
                false
        );
    }

    /**
     * Constructs a new brush with custom values.
     *
     * @param name    The name of the brush
     * @param mode    The mode the brush draws in
     * @param width   The width of the brush
     * @param height  The height of the brush
     * @param opacity The opacity of the brush
     * @param density The density of the brush
     * @param widthPressure If or not the width is affected by touch/pen pressure
     * @param heightPressure If or not the height is affected by touch/pen pressure
     * @param opacityPressure If or not the opacity is affected by touch/pen pressure
     * @param densityPressure If or not the density is affected by touch/pen pressure
     */
    public Brush(String name, Mode mode,
                 RangedInteger width, RangedInteger height, Integer opacity, Integer density,
                 Boolean widthPressure, Boolean heightPressure, Boolean opacityPressure, Boolean densityPressure) {
        this.brushProperties = new BrushProperties(
                name,
                mode,
                width,
                height,
                opacity,
                density,
                widthPressure,
                heightPressure,
                opacityPressure,
                densityPressure
        );
    }

    /**
     * Constructs a new brush with the given properties.
     *
     * @param brushProperties The properties
     */
    public Brush(BrushProperties brushProperties) {
        this.brushProperties = brushProperties;
    }

    /**
     * Passes brush information into the brush script.
     *
     * @param location The mouse location
     */
    public void paintScript(Point location) {
        this.brushScript.paint(CompatibilityUtil.getInstance().paintableCanvas, location, brushProperties);
    }
}
