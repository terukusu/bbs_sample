package org.terukusu.example.util.db;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import org.terukusu.example.util.db.BasicBeanProcessor;
import org.terukusu.example.util.db.ColumnProcessor;
import org.terukusu.example.util.db.DataAccessException;

/**
 * DBの値を unixtimestamp とみなしてCalendar型プロパティにマップする {@link BeanProcessor} です。
 */
public class UnixtimeBeanProcessor extends BasicBeanProcessor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.terukusu.example.util.db.BasicBeanProcessor#getColumnProcessor(java.sql.
     * ResultSet)
     */
    @Override
    protected ColumnProcessor getColumnProcessor(ResultSet rs, Class<?> type) {
        ColumnProcessor cp = new ColumnProcessor() {

            /*
             * (non-Javadoc)
             * 
             * @see org.terukusu.example.util.db.ColumnProcessor#process(java.sql.ResultSet,
             * int, java.lang.Class)
             */
            @Override
            public Object process(ResultSet rs, int column, Class<?> type) {
                // このPJでは日付は、JavaBean側はCalendar, DB側はlong(= unixtimestamp)として扱う

                try {
                    ResultSetMetaData rsmd = rs.getMetaData();

                    String colName = rsmd.getColumnName(column);
                    Method setter = findCompatibleSetter(type, colName);

                    Object value = rs.getObject(column);

                    if (Calendar.class.isAssignableFrom(setter.getParameterTypes()[0])) {
                        // カレンダ型のプロパティ
                        Calendar cal = Calendar.getInstance();
                        Number numValue = (Number) value;
                        if (numValue != null) {
                            cal.setTimeInMillis(numValue.longValue() * 1000);
                        }
                        value = cal;
                    }

                    return value;
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }
            }

        };

        return cp;
    }

}
