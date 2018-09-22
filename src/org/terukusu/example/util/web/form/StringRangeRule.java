package org.terukusu.example.util.web.form;

import java.util.Arrays;

public class StringRangeRule implements ValidatorRule {

    private final int min;
    private final int max;
    
    public StringRangeRule(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String[])
     */
    @Override
    public boolean validate(String[] values) {
        return values == null || values.length == 0
                || Arrays.stream(values).allMatch(this::validate);
    }

    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        if (value == null || value.trim().length() == 0) {
            return true;
        }

        return (value.length() >= getMin() && value.length() <= getMax());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StringRangeRule [min=" + min + ", max=" + max + "]";
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }
}
