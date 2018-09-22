package org.terukusu.example.util.db.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

import org.terukusu.example.util.StringUtil;
import org.terukusu.example.util.db.DataAccessException;
import org.terukusu.example.util.db.QueryUtil;

public abstract class BaseDao<T extends Entity<I>, I> implements Dao<T, I> {

    public T findById(I id) {

        String tableName = getTableName();
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        Class<T> entityClass = getEntityClass();

        List<T> result = QueryUtil.getInstance().queryAsBean(sql, entityClass, id);
        T entity = null;
        if (!result.isEmpty()) {
            entity = result.get(0);
        }

        return entity;
    }

    public List<T> findAll() {
        String tableName = getTableName();
        String sql = "SELECT * FROM " + tableName;
        Class<T> entityClass = getEntityClass();

        List<T> result = QueryUtil.getInstance().queryAsBean(sql, entityClass);

        return result;
    }

    public int deleteById(I id) {

        String tableName = getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE id=?";

        int result = QueryUtil.getInstance().update(sql, id);

        return result;
    }

    protected void prepareUpDate(T entity) {
        entity.setUpdatedAt(Calendar.getInstance());
    }

    protected void prepareAdd(T entity) {
        entity.setCreatedAt(Calendar.getInstance());
        entity.setUpdatedAt(Calendar.getInstance());
    }

    abstract public T add(T entity);

    abstract public T save(T entity);

    protected String getTableName() {
        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
        String[] fqcn = t.getActualTypeArguments()[0].getTypeName().split("\\.");
        String simpleName = fqcn[fqcn.length - 1];
        String tableName = StringUtil.camel2snake(simpleName);

        return tableName;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
        String fqcn = t.getActualTypeArguments()[0].getTypeName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fqcn);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException(e);
        }

        return (Class<T>) clazz;
    }
}
