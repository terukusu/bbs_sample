package org.terukusu.example.util.db.dao;

import java.util.List;

import org.terukusu.example.util.db.DataAccessException;

/**
 * データソースへアクセスするための基本的なインターフェイスです。
 *
 * @param <T>
 *            扱う {@link Entity} の実装クラス。
 * @param <I>
 *            扱う {@link Entity} の ID の型。
 */
public interface Dao<T extends Entity<I>, I> {
    /**
     * IDを指定してエンティティを検索します。
     * 
     * @param id
     *            IDです。
     * @return 検索結果です。見つからなかった場合は {@code null}
     */
    T findById(I id);

    /**
     * すべてのエンティティを取得します。
     * 
     * @return {@link Entity} の {@link List} です。
     */
    List<T> findAll();

    /**
     * エンティティを追加します。 データソースに既に存在するIDが指定されている場合は {@link DataAccessException}
     * がスローされます。
     * 
     * @param entity
     *            エンティティ。
     * @return 追加されたエンティティ。IDが自動採番されている場合は採番されたIDが設定された状態で返します。
     */
    T add(T entity);

    /**
     * エンティティを追加します。 データソースに既に存在するIDが設定されている場合はそのレコードが更新されます。 IDが設定されていない場合は
     * {@link DataAccessException} がスローされます。
     * 
     * @param entity
     *            エンティティ。
     * @return 追加されたエンティティ。IDが自動採番されている場合は採番されたIDが設定された状態で返します。
     */
    T save(T entity);

    /**
     * IDを指定してエンティティを削除します。
     * 
     * @param id
     *            IDです。
     * @return 削除された行数です。
     */
    int deleteById(I id);
}
