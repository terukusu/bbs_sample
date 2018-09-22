package org.terukusu.example.util.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import org.terukusu.example.util.StringUtil;

public class BasicBeanProcessor implements BeanProcessor {

    private ColumnProcessor columnProcessor = new BasicColumnProcessor();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.terukusu.example.util.db.BeanProcessor#createBean(java.sql.ResultSet,
     * java.lang.Class)
     */
    @Override
    public <T> T createBean(ResultSet rs, Class<? extends T> type) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            T row = type.getDeclaredConstructor().newInstance();
            int colmunCount = rsmd.getColumnCount();
            ColumnProcessor cp = getColumnProcessor(rs, type);
            for (int i = 0; i < colmunCount; i++) {
                int col = i + 1;
                String colName = rsmd.getColumnName(col);

                Object value = null;
                value = cp.process(rs, col, type);

                Method setter = findCompatibleSetter(type, colName);

                if (setter == null) {
                    throw new DataAccessException(
                            "setter not found: bean=" + row + ", colName=" + colName + ", value=" + value);
                }

                setter.invoke(row, value);
            }

            return row;
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new DataAccessException(e);
        }
    }

    protected Method findCompatibleSetter(Class<?> type, String colName) {
        String setterName = "set" + StringUtil.capitalize(StringUtil.snake2camel(colName));

        Method setter = Arrays.stream(type.getMethods())
                .filter(x -> x.getName().equals(setterName) && x.getParameterCount() == 1)
                .findFirst().orElse(null);

        return setter;
    }

    protected ColumnProcessor getColumnProcessor(ResultSet rs, Class<?> type) {
        return this.columnProcessor;
    }
}
