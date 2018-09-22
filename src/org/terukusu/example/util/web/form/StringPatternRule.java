package org.terukusu.example.util.web.form;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringPatternRule implements ValidatorRule {

    private final Pattern pattern;
    
    public StringPatternRule(Pattern pattern) {
        this.pattern = pattern;
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

        return (getPattern().matcher(value).matches());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StringPatternRule [pattern=" + pattern + "]";
    }

    /**
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }
}
