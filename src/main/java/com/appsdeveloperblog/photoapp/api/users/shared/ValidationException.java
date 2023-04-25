package com.appsdeveloperblog.photoapp.api.users.shared;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
