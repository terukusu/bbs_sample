package org.terukusu.example.util.web.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestIntRangeRule {

    private IntRangeRule rule;
    
    @BeforeEach
    void setUp() throws Exception {
        rule = new IntRangeRule(0, 5);
    }

    @AfterEach
    void tearDown() throws Exception {
        rule = null;
    }

    @Test
    void testValidateCaseInRange() {
        boolean result = false;
        
        result = rule.validate(new String[] {"3"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseExceedUnderLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"-1"});
        assertFalse("result should be true.", result);
    }

    @Test
    void testValidateCaseOnUnderLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"0"});
        assertTrue("result should be true.", result);
    }


    @Test
    void testValidateCaseExceedUpperLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"5"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseOnUpperLimit() {
        boolean result = false;
        
        result = rule.validate(new String[] {"6"});
        assertFalse("result should be true.", result);
    }

    @Test
    void testValidateCaseFloat() {
        boolean result = false;
        
        result = rule.validate(new String[] {"3.5"});
        assertFalse("result should be true.", result);
    }

    @Test
    void testValidateCaseMultiValueAllInRange() {
        boolean result = false;
        
        result = rule.validate(new String[] {"0","1"});
        assertTrue("result should be true.", result);
    }

    @Test
    void testValidateCaseMultiValueOneOutOfRange() {
        boolean result = false;
        
        result = rule.validate(new String[] {"0","6"});
        assertFalse("result should be true.", result);
    }
    
    @Test
    void testValidateCaseInvalideNuberFormat() {
        boolean result = false;
        
        result = rule.validate(new String[] {"a"});
        assertFalse("result should be true.", result);
    }
}
