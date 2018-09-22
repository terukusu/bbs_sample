package org.terukusu.example.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern ACCESSOR_PATTERN = Pattern.compile("(?:(?:set)|(?:get))([A-Z]+.*)");

    /**
     * 最初の一文字を大文字に変換します。
     * 
     * @param str
     *            文字列
     * @return 最初の一文字を大文字に変換した文字列
     */
    public static String capitalize(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);

        return new String(chars);
    }

    /**
     * 最初の一文字を子文字に変換します。
     * 
     * @param str
     *            文字列
     * @return 最初の一文字を子文字に変換した文字列
     */
    public static String uncapitalize(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);

        return new String(chars);
    }

    /**
     * キャメルケースの文字列をスネークケースに変換します。
     * 
     * @param str
     *            キャメルケースの文字列です
     * @return スネークケースの文字列です
     */
    public static String camel2snake(String str) {
        if (isEmty(str)) {
            return str;
        }

        String snake = str
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

        return snake;
    }

    /**
     * スネークケースの文字列をキャメルケースに変換します。
     * 
     * @param str
     *            スネークケースの文字列です
     * @return キャメルケースの文字列です
     */
    public static String snake2camel(String str) {
        if (isEmty(str)) {
            return str;
        }

        String[] words = str.split("_");

        String camel = Arrays.stream(words).reduce((s1, s2) -> {
            char[] chars = s2.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return s1 + new String(chars);
        }).get();

        return camel;
    }

    /**
     * 文字列が空でないこと(nullでもなく空文字列でのないこと)を確かめます。
     * 
     * @param str
     *            文字列です。
     * @return true: 文字列が空でない(nullでもなく空文字列でのない)。
     */
    public static boolean isEmty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * アクセッサ(getter/setter)名をプロパティ名に変換します。 例: getName → name
     * 
     * @param accessor
     *            アクセッサ名です
     * @return プロパティ名です。命名規則違反の場合はnull
     */
    public static String accessorToProperty(String accessor) {

        Matcher m = ACCESSOR_PATTERN.matcher(accessor);
        if (!m.matches()) {
            return null;
        }

        String propertyName = uncapitalize(m.group(1));

        return propertyName;
    }
}
