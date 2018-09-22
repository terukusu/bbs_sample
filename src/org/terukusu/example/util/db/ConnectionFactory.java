package org.terukusu.example.util.db;

import java.sql.Connection;

/**
 * DBへの新しい接続を生成するインターフェイスです。
 */
public interface ConnectionFactory {
    /**
     * DBへの新しい接続を生成します。
     * @return DBへの新しい接続です。
     */
    Connection createConnection();
}
