package org.terukusu.example.util.web.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestNotEmptyRule {

    private NotEmptyRule rule;
    
    @BeforeEach
    void setUp() throws Exception {
        this.rule = new NotEmptyRule();
    }

    @AfterEach
    void tearDown() throws Exception {
        this.rule = null;
    }

    @Test
    void testValidate() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseNull() {
        boolean result = false;
        
        result = rule.validate((String[])null);
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseZeroLengthArray() {
        boolean result = false;
        
        result = rule.validate(new String[] {});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseEmptyString() {
        boolean result = false;
        
        result = rule.validate(new String[] {""});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseNullElement() {
        boolean result = false;
        
        result = rule.validate(new String[] {null});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseMultiValue() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a", "b"});
        assertTrue("result should be true.", result);
    }


    @Test
    void testValidateCaseMultiValueOneIsEmptyString() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a",""});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseMultiValueOneIsNull() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a",null});
        assertFalse("result should be false.", result);
    }
}
