package org.terukusu.example.util.web.form;

import java.util.Arrays;

/**
 * 浮動小数点数のバリデータです。
 */
public class DoubleRangeRule implements ValidatorRule {

    private final double min;
    private final double max;
    
    public DoubleRangeRule(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String[])
     */
    @Override
    public boolean validate(String[] values) {
        return values == null || Arrays.stream(values).allMatch(this::validate);
    }

    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        
        if (value == null || value.trim().length() == 0) {
            return true;
        }
        
        try {
            double f = Double.parseDouble(value);
            return (f >= getMin() && f <= getMax());
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DoubleRangeRule [min=" + min + ", max=" + max + "]";
    }

    /**
     * @return the min
     */
    public double getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public double getMax() {
        return max;
    }
}
