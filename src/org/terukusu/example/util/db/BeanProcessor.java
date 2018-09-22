package org.terukusu.example.util.db;

import java.sql.ResultSet;

/**
 * DBの行をJavaBeanに変換するプロセッサのインターフェイスです。
 */
public interface BeanProcessor {
    /**
     * DBの行をJavaBeanに変換するプロセッサのインターフェイスです。
     * 
     * @param rs
     *            リザルトセット。現在行が処理対象になります
     * @param type
     *            JavaBeanのタイプ
     * @return DBの行をJavaBeanへ変換したオブジェクト
     */
    <T> T createBean(ResultSet rs, Class<? extends T> type);
}
