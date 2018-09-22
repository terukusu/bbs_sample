package org.terukusu.example.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicColumnProcessor implements ColumnProcessor {

    @Override
    public Object process(ResultSet rs, int column, Class<?> type) {
        try {
            return rs.getObject(column);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
