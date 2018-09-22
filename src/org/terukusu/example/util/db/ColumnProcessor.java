package org.terukusu.example.util.db;

import java.sql.ResultSet;

/**
 * DBのカラムからJavaBeanのプロパティへオブジェクトを変換するためのインターフェイスです。
 *
 */
public interface ColumnProcessor {

    /**
     * DBのカラムからJavaBeanのプロパティへオブジェクトを変換します。
     * 
     * @param rs
     *            リザルトセット。現在行が処理対象になります
     * @param column
     *            カラム
     * @param type
     *            JavaBeanのタイプ
     * @return DBのカラムからJavaBeanのプロパティへ変換したオブジェクト
     */
    Object process(ResultSet rs, int column, Class<?> type);
}
