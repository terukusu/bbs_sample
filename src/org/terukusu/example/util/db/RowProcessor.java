package org.terukusu.example.util.db;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * ResultSetの行をJavaで扱いやすい形式に変換するインターフェイスです。
 */
public interface RowProcessor {
    List<Object> toList(ResultSet rs);

    Map<String, Object> toMap(ResultSet rs);

    <T> T toBean(ResultSet rs, Class<? extends T> type);
}
