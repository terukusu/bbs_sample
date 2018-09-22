package org.terukusu.example.sample1;

import org.terukusu.example.util.db.DataAccessException;
import org.terukusu.example.util.db.QueryUtil;
import org.terukusu.example.util.db.dao.BaseDao;

public class SamplePersonDao extends BaseDao<SamplePerson, Integer> {

    /*
     * (non-Javadoc)
     * 
     * @see org.terukusu.example.dbsampleweb.dao.BaseDao#add(org.terukusu.example.
     * dbsampleweb.model.Entity)
     */
    @Override
    public SamplePerson add(SamplePerson entity) {

        prepareAdd(entity);

        String sql = "INSERT INTO " + getTableName()
                + " (id, name, age, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        Object[] params = new Object[] {
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getCreatedAt().getTimeInMillis() / 1000,
                entity.getUpdatedAt().getTimeInMillis() / 1000,
        };
        QueryUtil.getInstance().update(sql, params);

        // 自動採番のIDも含めて今回挿入した行を取得する
        SamplePerson result = entity;
        if (entity.getId() == null) {
            sql = "SELECT * FROM " + getTableName() + " WHERE ROWID=last_insert_rowid()";
            result = QueryUtil.getInstance().queryAsBean(sql, getEntityClass()).get(0);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.terukusu.example.dbsampleweb.dao.BaseDao#save(org.terukusu.example.
     * dbsampleweb.model.Entity)
     */
    @Override
    public SamplePerson save(SamplePerson entity) {

        if (entity.getId() == null) {
            throw new DataAccessException("id should not be null: " + entity);
        }

        if (entity.getCreatedAt() == null) {
            // 新規
            prepareAdd(entity);
        } else {
            // 更新
            prepareUpDate(entity);
        }

        String sql = "REPLACE INTO " + getTableName()
                + " (id, name, age, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        Object[] params = new Object[] {
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getCreatedAt().getTimeInMillis() / 1000,
                entity.getUpdatedAt().getTimeInMillis() / 1000,
        };
        QueryUtil.getInstance().update(sql, params);

        SamplePerson result = entity;

        return result;
    }
 }
