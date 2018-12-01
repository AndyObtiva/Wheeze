package com.deflatedpickle.wheeze.brush;

import com.deflatedpickle.wheeze.util.Size;

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

    /**
     * The name of the brush.
     */
    @SuppressWarnings("WeakerAccess")
    public String name;
    /**
     * The brush mode.
     */
    @SuppressWarnings("WeakerAccess")
    public Mode mode;
    /**
     * The width of the brush.
     */
    @SuppressWarnings("WeakerAccess")
    public Size width;
    // /**
    //  * If or not the width is controlled by touch/pen pressure.
    //  */
    // @SuppressWarnings("WeakerAccess")
    // public Boolean widthByPressure;
    /**
     * The height of the brush.
     */
    @SuppressWarnings("WeakerAccess")
    public Size height;
    // /**
    //  * If or not the height is controlled by touch/pen pressure.
    //  */
    // @SuppressWarnings("WeakerAccess")
    // public Boolean heightByPressure;
    /**
     * The opacity of the brush.
     */
    @SuppressWarnings("WeakerAccess")
    public Integer opacity;
    // /**
    //  * If or not the opacity is controlled by touch/pen pressure.
    //  */
    // @SuppressWarnings("WeakerAccess")
    // public Boolean opacityByPressure;
    /**
     * The density of the brush.
     */
    @SuppressWarnings("WeakerAccess")
    public Float density;
    // /**
    //  * If or not the density is controlled by touch/pen pressure.
    //  */
    // @SuppressWarnings("WeakerAccess")
    // public Boolean densityByPressure;

    /**
     * Constructs a new brush with the default values.
     */
    public Brush() {
        this.name = "Untitled Brush";
        this.mode = Mode.CONTINUOUS;

        this.width = new Size(20f, 0);
        this.height = new Size(20f, 0);
        this.opacity = 100;
        this.density = 1f;
    }

    /**
     * Constructs a new brush with custom values.
     *
     * @param name The name of the brush.
     * @param mode The mode the brush draws in.
     * @param width The width of the brush.
     * @param height The height of the brush.
     * @param opacity The opacity of the brush.
     * @param density The density of the brush.
     * // @param widthByPressure If or not the width is affected by touch/pen pressure.
     * // @param heightByPressure If or not the height is affected by touch/pen pressure.
     * // @param opacityByPressure If or not the opacity is affected by touch/pen pressure.
     * // @param densityByPressure If or not the density is affected by touch/pen pressure.
     */
    public Brush(String name, Mode mode,
                 Size width, Size height, Integer opacity, Float density /*,
                 Boolean widthByPressure, Boolean heightByPressure, Boolean opacityByPressure, Boolean densityByPressure*/) {
        this.name = name;
        this.mode = mode;

        this.width = width;
        this.height = height;
        this.opacity = opacity;
        this.density = density;

        // this.widthByPressure = widthByPressure;
        // this.heightByPressure = heightByPressure;
        // this.opacityByPressure = opacityByPressure;
        // this.densityByPressure = densityByPressure;
    }

    /**
     * Places a single paint of the brush
     *
     * @param penPressure The current tablet pressure applied.
     */
    public void paint(float penPressure) {
    }
}
