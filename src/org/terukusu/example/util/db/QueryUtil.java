package org.terukusu.example.util.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.terukusu.example.util.Config;
import org.terukusu.example.util.Log;
import org.terukusu.example.util.StringUtil;

public class QueryUtil {

    private static QueryUtil instance;
    private RowProcessor rowProcessor = new BasicRowProcessor();

    public synchronized static QueryUtil getInstance() {
        if (instance == null) {
            instance = new QueryUtil();
            
            // カスタム BeanProcessor の指定が有れば反映する
            String beanProcessorClassName = Config.get("db.bean.processor.class");
            if (!StringUtil.isEmty(beanProcessorClassName)) {
                BeanProcessor beanProcessor = null;
                try {
                    beanProcessor = (BeanProcessor)Class.forName(beanProcessorClassName).getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                    throw new DataAccessException(e);
                }
                BasicRowProcessor rowProcessor = new BasicRowProcessor();
                rowProcessor.setBeanProcessor(beanProcessor);
                instance.setRowProcessor(rowProcessor);
            }
        }

        return instance;
    }

    protected QueryUtil() {
    }

    public <T> List<T> queryAsBean(String sql, Class<? extends T> type, Object... params) {
        List<T> result = new ArrayList<>();

        try (ResultSet rs = doQuery(sql, params)) {
            RowProcessor rp = getRowProcessor();
            while (rs.next()) {
                T entity = rp.toBean(rs, type);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return result;
    }

    public List<List<Object>> queryAsList(String sql, Object... params) {
        List<List<Object>> result = new ArrayList<>();

        try (ResultSet rs = doQuery(sql, params)) {
            RowProcessor rp = getRowProcessor();
            while (rs.next()) {
                result.add(rp.toList(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return result;
    }

    public List<Map<String, Object>> queryAsMap(String sql, Object... params) {
        List<Map<String, Object>> result = new ArrayList<>();

        try (ResultSet rs = doQuery(sql, params)) {
            RowProcessor rp = getRowProcessor();
            while (rs.next()) {
                result.add(rp.toMap(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return result;
    }

    /**
     * SQLを実行しリザルトセットを取得します。
     * 
     * @param sql
     *            SQL
     * @return リザルトセット
     */
    public ResultSet doQuery(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Log.debug("query: " + sql + ", params: " +
                (params != null ? Arrays.asList(params) : "null"));

        conn = ConnectionManager.getInstance().getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    int col = i + 1;
                    pstmt.setObject(col, params[i]);
                }
            }

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return rs;
    }

    /**
     * SQLを実行しリザルトセットを取得します。
     * 
     * @param sql
     *            SQL
     * @return リザルトセット
     */
    public int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        Log.debug("query: " + sql + ", params: " +
                (params != null ? Arrays.asList(params) : "null"));

        conn = ConnectionManager.getInstance().getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    int col = i + 1;
                    pstmt.setObject(col, params[i]);
                }
            }

            int updated = pstmt.executeUpdate();
            return updated;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * @return the rowProcessor
     */
    public RowProcessor getRowProcessor() {
        return rowProcessor;
    }

    /**
     * @param rowProcessor
     *            the rowProcessor to set
     */
    public void setRowProcessor(RowProcessor rowProcessor) {
        this.rowProcessor = rowProcessor;
    }
}
