package org.terukusu.example.util.web.form;

public interface ValidatorRule {
    /**
     * フォームの値を検証します。
     * @param value
     * @return
     */
    boolean validate(String[] values);

    /**
     * フォームの値を検証します。
     * @param value
     * @return
     */
    boolean validate(String value);
}
