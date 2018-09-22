package org.terukusu.example.util.web.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStringPatternRule {

    private StringPatternRule rule;
    
    @BeforeEach
    void setUp() throws Exception {
        this.rule = new StringPatternRule(Pattern.compile("a.+f"));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.rule = null;
    }

    @Test
    void testValidate() {
        boolean result = false;
        
        result = rule.validate(new String[] {"asdf"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseNull() {
        boolean result = false;
        
        result = rule.validate((String[])null);
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseZeroLengthArray() {
        boolean result = false;
        
        result = rule.validate(new String[] {});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseEmptyString() {
        boolean result = false;
        
        result = rule.validate(new String[] {""});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseNullElement() {
        boolean result = false;
        
        result = rule.validate(new String[] {null});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseNotMatch() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a"});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseMultiValueOneIsNotMatch() {
        boolean result = false;
        
        result = rule.validate(new String[] {"asdf","a"});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseMultiValueOneIsNull() {
        boolean result = false;
        
        result = rule.validate(new String[] {"asdf", null});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseMultiValueOneIsEmpty() {
        boolean result = false;
        
        result = rule.validate(new String[] {"asdf",""});
        assertTrue("result should be true.", result);
    }
}
