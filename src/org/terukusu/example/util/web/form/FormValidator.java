package org.terukusu.example.util.web.form;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

public class FormValidator {
    private final Map<String, Set<ValidatorRule>> ruleMap = new ConcurrentHashMap<>();

    public FormValidator() {
    }

    public Set<String> validate(Map<String, String[]> formData) {
        final Set<String> invalidSet = new ConcurrentSkipListSet<>();

        getRuleMap().keySet().stream().forEach(name -> {
            if(!validate(name, formData.get(name))) {
                invalidSet.add(name);
            }            
        });
        
        return invalidSet;
    }

    public boolean validate(String name, final String[] values) {
        return getRuleMap().get(name).stream().allMatch(rule -> rule.validate(values));
    }

    /**
     * 指定したパラメータ名リストに対して「必須」であるというルールを設定します。
     * @param names パラメータ名リスト
     */
    public FormValidator addNotEmptyRule(String... names) {
        Arrays.stream(names).forEach(name -> addRule(name, new NotEmptyRule()));
        return this;
    }
    
    /**
     * 指定されたパラメータ名に対して、整数値で有ること、
     * かつ、範囲内であるというルールを設定します。
     * 
     * @param name パラメータ名
     * @param min 範囲の下限(この値は有効な値に含みます)
     * @param max 範囲の上限(この値は有効な値に含みます)
     */
    public FormValidator addIntRangeRule(String name, int min, int max) {
       addRule(name, new IntRangeRule(min, max)); 
       return this;
    }
    
    /**
     * 指定されたパラメータ名に対して浮動小数点数(整数も含む)で有ること
     * かつ、範囲内であるというルールを設定します。
     * 
     * @param name パラメータ名
     * @param min 範囲の下限(この値は有効な値に含みます)
     * @param max 範囲の上限(この値は有効な値に含みます)
     */
    public FormValidator addDoubleRangeRule(String name, double min, double max) {
        addRule(name, new DoubleRangeRule(min, max));
        return this;
    }
    
    /**
     * 指定されたパラメータ名に対して文字列で有ること
     * かつ、文字長が範囲内であるというルールを設定します。
     * 
     * @param name パラメータ名
     * @param min 範囲の下限(この値は有効な値に含みます)
     * @param max 範囲の上限(この値は有効な値に含みます)
     */
    public FormValidator addStringRangeRule(String name, int min, int max) {
        addRule(name, new StringRangeRule(min, max));
        return this;
    }

    /**
     * 指定されたパラメータ名に対して指定されたパターンで有るというルールを設定します。
     * 
     * @param name パラメータ名
     * @param pattern パターン({@link Pattern})です
     */
    public FormValidator addStringPatternRule(String name, String pattern) {
        addRule(name, new StringPatternRule(Pattern.compile(pattern)));
        return this;
    }
    
    /**
     * 指定されたパラメータ名に対して指定された日付パターンで有るというルールを設定します。
     * 
     * @param name パラメータ名
     * @param dateFormat 日付フォーマットです {@link SimpleDateFormat} に使用するフォーマットと同じです。
     */
    public FormValidator addDateTimeRule(String name, String dateFormat) {
        addRule(name, new DateTimeRule(new SimpleDateFormat(dateFormat)));
        return this;
    }
    
    /**
     * 指定されたパラメータ名に対して指定されたルールを追加します。
     * 
     * @param name パラメータ名
     * @param rule ルール
     */
    public FormValidator addRule(String name, ValidatorRule rule) {
        Set<ValidatorRule> ruleSet = getRuleMap().get(name);
        if (ruleSet == null) {
            ruleSet = new CopyOnWriteArraySet<ValidatorRule>();
            getRuleMap().put(name, ruleSet);
        }
        ruleSet.add(rule);
        
        return this;
    }

    /**
     * @return the ruleMap
     */
    public Map<String, Set<ValidatorRule>> getRuleMap() {
        return ruleMap;
    }
}
