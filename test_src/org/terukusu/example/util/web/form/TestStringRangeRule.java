package org.terukusu.example.util.web.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStringRangeRule {

    private StringRangeRule rule;
    
    @BeforeEach
    void setUp() throws Exception {
        this.rule = new StringRangeRule(2, 5);
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
    void testValidateCaseNullElement() {
        boolean result = false;
        
        result = rule.validate(new String[] {null});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseEmptyString() {
        boolean result = false;
        
        result = rule.validate(new String[] {""});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseOnUnderLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"aa"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseExceedUnderLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a"});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseOnUpperLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"aaaaa"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseExceedUpperLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"aaaaaa"});
        assertFalse("result should be false.", result);
    }
}
