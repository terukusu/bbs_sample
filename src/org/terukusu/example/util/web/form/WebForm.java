package org.terukusu.example.util.web.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.terukusu.example.util.Log;

public abstract class WebForm {
    
    /**
     * フォームデータを検証します。
     * 
     * @param formData
     * @return 検証が失敗したパラメータ名の {@Code Set} です。すべて成功した場合は空の {@link Set} です。
     */
    abstract public Set<String> parse(Map<String, String[]> formData);

    /**
     * 日付型のパラメータ値をパースします。
     * @param format フォーマットです。
     * @param value パラメータ値です。
     * @return パラメータ値が表す日付です
     */
    protected Calendar parseDateParameter(String format, String value) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(value));
        } catch (ParseException e) {
            // 検証済なのでエラーなど起きないはず。
            Log.warn(e);
            throw new RuntimeException(e);
        }
        
        return cal;
    }
}
