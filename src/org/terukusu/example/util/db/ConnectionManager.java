package org.terukusu.example.util.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import org.terukusu.example.util.Config;
import org.terukusu.example.util.Log;

/**
 * DB接続を管理するための {@link ConnectionManager} 実装です。
 *
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private ConnectionFactory connectionFactory;
    
    final ThreadLocal<Connection> threadLoacaConnection = new ThreadLocal<>();

    public synchronized static ConnectionManager getInstance() {
        if (instance == null) {
            // コネクションファクトリ生成
            String cfName = Config.get("db.connection.factory.class");
            ConnectionFactory cf = null;
            try {
                cf = (ConnectionFactory) Class.forName(cfName).getConstructor().newInstance();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException e) {
                throw new DataAccessException(e);
            }
            
            ConnectionManager cm = new ConnectionManager();
            cm.setConnectionFactory(cf);
            
            instance = cm;
        }
        
        return instance;
    }
    
    protected ConnectionManager() {
    }
    
    public Connection getConnection() {
        Connection conn = getThreadLoacaConnection().get();
        if (conn == null) {
            conn = getConnectionFactory().createConnection();
            getThreadLoacaConnection().set(conn);
        }
        
        return conn;
    }

    public void begin() {
        Log.debug("begin transaction.");
        try {
            // for Java9 or later
            // getConnection().beginRequest();

            getConnection().createStatement().executeQuery("BEGIN");

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void rollback() {
        try {
            Log.debug("rollbacking transaction.");
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void commit() {
        try {
            Log.debug("committin transaction.");
            getConnection().commit();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void closeConnection() {
        try {
            Log.debug("closing connection.");
            getConnection().close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        getThreadLoacaConnection().remove();
    }

    /**
     * @return the connectionFactory
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * @param connectionFactory the connectionFactory to set
     */
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * @return the threadLoacaConnection
     */
    public ThreadLocal<Connection> getThreadLoacaConnection() {
        return threadLoacaConnection;
    }
}
