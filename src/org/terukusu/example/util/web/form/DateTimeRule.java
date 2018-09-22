package org.terukusu.example.util.web.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;

/**
 * 浮動小数点数のバリデータです。
 */
public class DateTimeRule implements ValidatorRule {

    private final DateFormat dateFormat;
    
    public DateTimeRule(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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
            getDateFormat().parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DateTimeRule [dateFormat=" + dateFormat + "]";
    }

    /**
     * @return the dateFormat
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }
}
