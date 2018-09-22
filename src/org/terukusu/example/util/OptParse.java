package org.terukusu.example.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptParse {

    private final Map<String, Option> optionMap = new HashMap<String, Option>();

    private static final OptParse instance = new OptParse();

    public static OptParse parser() {
        return instance;
    }

    protected OptParse() {
    }

    public OptParse add(String optionKey, boolean hasValue, boolean required) {
        Option option = new Option(optionKey, hasValue, required);

        this.optionMap.put(optionKey, option);

        return parser();
    }

    public OptParse reset() {
        this.optionMap.clear();
        return parser();
    }

    public Map<String, String> parse(String[] args) {

        Map<String, String> result = new HashMap<>();

        Option option = null;

        for (Iterator<String> it = Arrays.asList(args).iterator(); it.hasNext();) {
            String str = it.next();

            if (str.charAt(0) == '-') {
                // key
                option = this.optionMap.get(str);

                if (option == null) {
                    throw new IllegalArgumentException("unknown option: " + str);
                }

                if (!option.hasValue()) {
                    result.put(str, "true");
                    option = null;
                }
            } else {
                if (option == null) {
                    throw new IllegalArgumentException("key for value not specified: " + str);
                }

                result.put(option.getOptionKey(), str);
                option = null;
            }
        }

        this.optionMap.values().stream().forEach(v -> {
            if (v.isRequired() && !result.containsKey(v.getOptionKey())) {
                throw new IllegalArgumentException("required option not specified: " + v.getOptionKey());
            }
        });

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OptParse [optionMap=" + optionMap + "]";
    }
}
