package com.deflatedpickle.wheeze.util;

public class Size {
    /**
     * The maximum value of the size.
     */
    private final int valueMax;
    /**
     * The current value of the size.
     */
    private int valueCurrent;
    /**
     * The minimum value of the size, as a percentage of the maximum.
     */
    // TODO: Set and use the minimum value.
    // private final Integer valueMin;

    public Size(int valueMax, int valueMin) {
        this.valueMax = valueMax;
        // this.valueMin = valueMin;
    }

    public int getValueMax() {
        return valueMax;
    }

    public int getValueCurrent() {
        return valueCurrent;
    }

    // public Integer getValueMin() {
    //     return valueMin;
    // }

    public void setValueCurrent(int newValue) {
        if (newValue > valueMax /*&& newValue < valueMin*/) {
            valueCurrent = valueMax;
        }
    }
}
