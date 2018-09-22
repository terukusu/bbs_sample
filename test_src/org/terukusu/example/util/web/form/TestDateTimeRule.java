package org.terukusu.example.util.web.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateTimeRule {

    private DateTimeRule rule;
    
    @BeforeEach
    void setUp() throws Exception {
        this.rule = new DateTimeRule(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.rule = null;
    }

    @Test
    void testValidate() {
        boolean result = false;
        
        result = rule.validate(new String[] {"2018-10-21 23:12:09 +0900"});
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
    void testValidateCaseInvalidFormat() {
        boolean result = false;
        
        result = rule.validate(new String[] {"asdf"});
        assertFalse("result should be false.", result);
    }

    @Test
    void testValidateCaseMultiValue() {
        boolean result = false;
        
        result = rule.validate(new String[] {"2018-10-21 23:12:09 +0900", "2018-01-11 01:01:09 +0900"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseMultiValueOneIsInvalid() {
        boolean result = false;
        
        result = rule.validate(new String[] {"2018-10-21 23:12:09 +0900", "xxxx-01-11 01:01:09 +0900"});
        assertFalse("result should be false.", result);
    }
}
