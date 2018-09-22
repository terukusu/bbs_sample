package org.terukusu.example.util.db;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.terukusu.example.util.Config;
import org.terukusu.example.util.Log;

/**
 * SQLite用のDB接続を生成する {@link ConnectionFactory} 実装です。
 */
public class SQLiteConnectionFactory implements ConnectionFactory {
    
    public Connection createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new DataAccessException(e);
        }

        Properties prop = new Properties();
        prop.put("journal_mode", "MEMORY");
        prop.put("sync_mode", "OFF");

        Path dbFile = Paths.get(Config.get("db.sqlite_db_file"));

        String dbHeader = "jdbc:sqlite:" + dbFile.toAbsolutePath();
        Connection conn = null;

        Log.debug("using db ulr: " + dbHeader);

        try {
            conn = DriverManager.getConnection(dbHeader, prop);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        
        return conn;
    }
}
