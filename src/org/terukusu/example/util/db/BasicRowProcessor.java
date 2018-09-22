package org.terukusu.example.util.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicRowProcessor implements RowProcessor {

    private BeanProcessor beanProcessor = new BasicBeanProcessor();

    public BasicRowProcessor() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.terukusu.example.util.db.RowProcessor#toList()
     */
    @Override
    public List<Object> toList(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            List<Object> row = new ArrayList<>();
            int colmunCount = rsmd.getColumnCount();
            for (int i = 0; i < colmunCount; i++) {
                int col = i + 1;
                row.add(rs.getObject(col));
            }

            return row;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.terukusu.example.util.db.RowProcessor#toMap()
     */
    @Override
    public Map<String, Object> toMap(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            Map<String, Object> row = new HashMap<>();
            int colmunCount = rsmd.getColumnCount();
            for (int i = 0; i < colmunCount; i++) {
                int col = i + 1;
                row.put(rsmd.getColumnName(col), rs.getObject(col));
            }

            return row;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.terukusu.example.util.db.RowProcessor#toBean(java.sql.ResultSet,
     * java.lang.Class)
     */
    @Override
    public <T> T toBean(ResultSet rs, Class<? extends T> type) {
        return getBeanProcessor().createBean(rs, type);
    }

    /**
     * @return the beanProcessor
     */
    public BeanProcessor getBeanProcessor() {
        return beanProcessor;
    }

    /**
     * @param beanProcessor
     *            the beanProcessor to set
     */
    public void setBeanProcessor(BeanProcessor beanProcessor) {
        this.beanProcessor = beanProcessor;
    }
}
