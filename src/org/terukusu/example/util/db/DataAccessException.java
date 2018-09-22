package org.terukusu.example.util.db;

/**
 * データアクセスで何らかの問題が発生したことを表す例外です。
 *
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
