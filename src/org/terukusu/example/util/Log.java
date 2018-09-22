package org.terukusu.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    public enum Level {
        OFF(10), DEBUG(20), INFO(30), WARN(40), FATAL(50);

        private final int value;

        Level(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    public static Level level = Level.DEBUG;

    public static boolean isDebugEnabled() {
        return (level.getValue() <= Level.DEBUG.getValue());
    }
    
    public static boolean isInfoEnabled() {
        return (level.getValue() <= Level.INFO.getValue());
    }

    public static boolean isWarnEnabled() {
        return (level.getValue() <= Level.WARN.getValue());
    }

    public static boolean isFatalEnabled() {
        return (level.getValue() <= Level.FATAL.getValue());
    }
    

    public static void debug(Object message) {
        if (level.getValue() <= Level.DEBUG.getValue()) {
            log(Level.DEBUG, message, null);
        }
    }

    public static void debug(Object message, Throwable t) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, message, t);
        }
    }

    public static void info(Object message) {
        if (isInfoEnabled()) {
            log(Level.INFO, message, null);
        }
    }

    public static void info(Object message, Throwable t) {
        if (isInfoEnabled()) {
            log(Level.INFO, message, t);
        }
    }

    public static void warn(Object message) {
        if (isWarnEnabled()) {
            log(Level.WARN, message, null);
        }
    }

    public static void warn(Object message, Throwable t) {
        if (isWarnEnabled()) {
            log(Level.WARN, message, t);
        }
    }

    public static void fatal(Object message) {
        if (isFatalEnabled()) {
            log(Level.FATAL, message, null);
        }
    }

    public static void fatal(Object message, Throwable t) {
        if (isFatalEnabled()) {
            log(Level.FATAL, message, t);
        }
    }

    public static void log(Level level, Object message, Throwable t) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        // ロギングメソッド呼び出し元のスタックは３つ目
        StackTraceElement caller = ste[3];

        long threadId = Thread.currentThread().getId();
        String className = caller.getClassName();

        String[] fqcn = className.split("\\.");
        String simpleClassName = fqcn[fqcn.length - 1];

        String methodName = caller.getMethodName();
        String fileName = caller.getFileName();
        int line = caller.getLineNumber();

        StringBuilder sb = new StringBuilder();
        sb.append(level);
        sb.append(" [").append(DATE_FORMAT.format(new Date())).append("]");
        sb.append(" (").append(threadId).append(")");
        sb.append(" ").append(simpleClassName).append(".").append(methodName);
        if (fileName != null) {
            sb.append("(").append(fileName).append(":").append(line).append(")");
        }
        sb.append(" ").append(message);

        System.out.println(sb.toString());

        if (t != null) {
            t.printStackTrace(System.out);
        }
    }
}
