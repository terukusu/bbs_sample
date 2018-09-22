package org.terukusu.example.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.terukusu.example.util.Config;
import org.terukusu.example.util.Log;

/**
 * SQLite用のDB接続を管理するための {@link ConnectionManager} 実装です。
 *
 */
public class MySQLConnectionFactory implements ConnectionFactory {

    public MySQLConnectionFactory() {
    }

    @Override
    public Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DataAccessException(e);
        }

        String dbName = Config.get("db.mysql.dbname");
        String dbHost = Config.get("db.mysql.host");
        String dbPort = Config.get("db.mysql.port");
        String dbOptions = Config.get("db.mysql.options");

        String dbHeader = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + dbOptions;
        Connection conn = null;

        Log.debug("using db ulr: " + dbHeader);

        try {
            conn = DriverManager.getConnection(dbHeader);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return conn;
    }
}
