package com.appsdeveloperblog.photoapp.api.users.shared;

import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ParameterMap extends HashMap<String, Object> {

    private Map<String, String> errorMap = new HashMap<>();
    private MessageSource messageSource = BeanUtil.getBean(MessageSource.class);

    public String getString(String key) {
        Object value = get(key);

        if (value == null) {
            return null;
        }

        if (!(value instanceof String)) {
            return value.toString();
        }

        return String.valueOf(value);
    }

    public void reject(String field, String message) {
        reject(field, message, new Object[] { });
    }

    public void reject(String field, String message, Object[] arguments) {
        message = messageSource.getMessage(message, arguments, message, Locale.getDefault());

        errorMap.put(field, message);
    }

    public boolean hasErrors() {
        return !errorMap.isEmpty();
    }

    public boolean hasError(String key) {
        return errorMap.containsKey(key);
    }

    public Map<String, String> getErrors() {
        return errorMap;
    }

    public String getError(String key) {
        return errorMap.get(key);
    }

    public FieldValidation validate(String fieldName) {
        return new FieldValidation(fieldName, getString(fieldName), this);
    }
}