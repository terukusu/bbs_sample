package org.terukusu.example.util.web.form;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFormValidator {

    private FormValidator formValidator; 
    
    @BeforeEach
    void setUp() throws Exception {
        this.formValidator = new FormValidator();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testValidate() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {"foo bar"});

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertTrue(invalidSet.isEmpty(), "invalidSet should be empty.");
    }
    
    @Test
    void testValidateCaseNull() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", null);

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertFalse(invalidSet.isEmpty(), "invalidSet should not be empty.");
    }
    
    @Test
    void testValidateCaseEmptyArray() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {});

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertFalse(invalidSet.isEmpty(), "invalidSet should not be empty.");
    }

    @Test
    void testValidateCaseEmptyString() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {""});

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertFalse(invalidSet.isEmpty(), "invalidSet should not be empty.");
    }

    @Test
    void testValidateCaseMulti() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {"asdf", "asfda"});

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertTrue(invalidSet.isEmpty(), "invalidSet should be empty.");
    }

    @Test
    void testValidateCaseMultiOneIsInvalid() {
        this.formValidator.addNotEmptyRule("name");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {"asdf", null});

        Set<String> invalidSet = null;
        
        invalidSet = this.formValidator.validate(formData);
        assertFalse(invalidSet.isEmpty(), "invalidSet should not be empty.");

        assertTrue(invalidSet.contains("name"), "invalidSet should contain invalid names.");
    }

    @Test
    void testValidateCaseAll() {
        this.formValidator.addNotEmptyRule("name", "age", "eye", "mail", "birth");
        this.formValidator.addStringRangeRule("name", 0, 12);
        this.formValidator.addIntRangeRule("age", 0, 100);
        this.formValidator.addDoubleRangeRule("eye", 0.01, 3.5);
        this.formValidator.addStringPatternRule("mail", "[a-zA-Z_\\.]+@[a-zA-Z_\\.]+");
        this.formValidator.addDateTimeRule("birth", "yyyy/MM/dd");

        Map<String, String[]> formData = new HashMap<>();
        formData.put("name", new String[] {"asdfssssssss"});
        formData.put("age", new String[] {"25"});
        formData.put("eye", new String[] {"0.25"});
        formData.put("mail", new String[] {"foobar@hogehoge.com"});
        formData.put("birth", new String[] {"2018/09/22"});

        Set<String> invalidSet = null;
        invalidSet = this.formValidator.validate(formData);
        assertTrue(invalidSet.isEmpty(), "invalidSet should be empty.");
    }
}
