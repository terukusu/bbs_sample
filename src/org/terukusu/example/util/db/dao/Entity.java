package org.terukusu.example.util.db.dao;

import java.util.Calendar;

/**
 * エンティティを表すインターフェイスです。
 *
 * @param <T>
 *            idプロパティの型
 */
public interface Entity<T> {
    /**
     * IDを取得します。
     * 
     * @return IDです。
     */
    T getId();

    Calendar getCreatedAt();

    void setCreatedAt(Calendar date);

    Calendar getUpdatedAt();

    void setUpdatedAt(Calendar date);
}
