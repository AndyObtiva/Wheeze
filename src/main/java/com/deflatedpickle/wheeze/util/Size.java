package com.deflatedpickle.wheeze.util;

public class Size {
    /**
     * The maximum value of the size.
     */
    private final Float valueMax;
    /**
     * The current value of the size.
     */
    private Float valueCurrent;
    /**
     * The minimum value of the size, as a percentage of the maximum.
     */
    // TODO: Set and use the minimum value.
    // private final Integer valueMin;

    public Size(Float valueMax, Integer valueMin) {
        this.valueMax = valueMax;
        // this.valueMin = valueMin;
    }

    public Float getValueMax() {
        return valueMax;
    }

    public Float getValueCurrent() {
        return valueCurrent;
    }

    // public Integer getValueMin() {
    //     return valueMin;
    // }

    public void setValueCurrent(Float newValue) {
        if (newValue > valueMax /*&& newValue < valueMin*/) {
            valueCurrent = valueMax;
        }
    }
}
