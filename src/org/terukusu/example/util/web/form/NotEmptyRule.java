package org.terukusu.example.util.web.form;

import java.util.Arrays;

public class NotEmptyRule implements ValidatorRule {

    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String[])
     */
    @Override
    public boolean validate(String[] values) {
       return (values != null && values.length > 0
                && Arrays.stream(values).allMatch(v -> validate(v)));
    }

    /* (non-Javadoc)
     * @see org.terukusu.example.util.web.ValidatorRule#validate(java.lang.String)
     */
    @Override
    public boolean validate(String value) {
        return  (value != null && value.trim().length() > 0);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NotEmptyRule []";
    }
}
