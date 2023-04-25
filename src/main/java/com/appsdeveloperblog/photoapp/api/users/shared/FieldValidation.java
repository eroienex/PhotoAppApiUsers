package com.appsdeveloperblog.photoapp.api.users.shared;

public class FieldValidation {

    private String fieldName;
    private String fieldValue;
    private ParameterMap parameterMap;

    public FieldValidation(String fieldName, String fieldValue, ParameterMap parameterMap) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.parameterMap = parameterMap;
    }

    public interface BooleanCondition {

        boolean isTrue(String fieldValue);
    }

    public FieldValidation required() {
        return required("validation.required");
    }

    public FieldValidation required(String message) {
        return condition(Validator::isEmpty, message);
    }

    public FieldValidation maxLength(int length) {
        return maxLength(length, "validation.maxLength");
    }

    public FieldValidation maxLength(int length, String message) {
        return condition(
                (fieldValue) -> {
                    if (Validator.isEmpty(fieldValue)) {
                        return false;
                    }

                    return fieldValue.length() > length;
                },
                message,
                new Object[] { length }
        );
    }

    public FieldValidation minLength(int length) {
        return minLength(length, "validation.minLength");
    }

    public FieldValidation minLength(int length, String message) {
        return condition(
                (fieldValue) -> {
                    if (Validator.isEmpty(fieldValue)) {
                        return false;
                    }

                    return fieldValue.length() < length;
                },
                message,
                new Object[] { length }
        );
    }

    public FieldValidation isFirstName() {
        return isFirstName("validation.isFirstName");
    }

    public FieldValidation isFirstName(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isFirstName(value);
        }, message);
    }

    public FieldValidation isLastName() {
        return isLastName("validation.isLastName");
    }

    public FieldValidation isLastName(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isFirstName(value);
        }, message);
    }

    public FieldValidation isEmail() {
        return isEmail("validation.isEmail");
    }

    public FieldValidation isEmail(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isEmail(value);
        }, message);
    }

    public FieldValidation isMobile() {
        return isMobile("validation.isMobile");
    }

    public FieldValidation isMobile(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isMobile(value);
        }, message);
    }

    public FieldValidation intMinValue(int min) {
        return intMinValue(min, "validation.intMinValue");
    }

    public FieldValidation intMinValue(int min, String message) {
        return condition(
                (fieldValue) -> {
                    if (Validator.isEmpty(fieldValue)) {
                        return false;
                    }

                    if (!Validator.isInt(fieldValue)) {
                        return false;
                    }

                    return Integer.parseInt(fieldValue) < min;
                },
                message,
                new Object[] { min }
        );
    }

    public FieldValidation intMaxValue(int max) {
        return intMaxValue(max, "validation.intMaxValue");
    }

    public FieldValidation intMaxValue(int max, String message) {
        return condition(
                (fieldValue) -> {
                    if (Validator.isEmpty(fieldValue)) {
                        return false;
                    }

                    if (!Validator.isInt(fieldValue)) {
                        return false;
                    }

                    return Integer.parseInt(fieldValue) > max;
                },
                message,
                new Object[] { max }
        );
    }

    public FieldValidation isDate() {
        return isDate("validation.isDate");
    }

    public FieldValidation isDate(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isDate(value);
        }, message);
    }

    public FieldValidation isGender() {
        return isGender("validation.isGender");
    }

    public FieldValidation isGender(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isGender(value);
        }, message);
    }

    public FieldValidation isAlphaNumeric() {
        return isAlphaNumeric("validation.invalidFormat");
    }

    public FieldValidation isAlphaNumeric(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isAlphaNumeric(value);
        }, message);
    }

    public FieldValidation isAlphaWithSpace() {
        return isAlphaWithSpace("validation.alphanumericWithSpace");
    }

    public FieldValidation isAlphaWithSpace(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isAlphaWithSpace(value);
        }, message);
    }

    public FieldValidation isNumber() {
        return isNumber("validation.isNumber");
    }

    public FieldValidation isNumber(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isNumber(value);
        }, message);
    }

    public FieldValidation isInt() {
        return isInt("validation.isInt");
    }

    public FieldValidation isInt(String message) {
        return condition((value) -> {
            if (Validator.isEmpty(value)) {
                return false;
            }

            return !Validator.isInt(value);
        }, message);
    }

    public FieldValidation condition(BooleanCondition condition) {
        return condition(condition, "validation.condition", new Object[] {});
    }

    public FieldValidation condition(BooleanCondition condition, String message) {
        return condition(condition, message, new Object[] {});
    }

    public FieldValidation condition(
            BooleanCondition condition,
            String message,
            Object[] arguments
    ) {
        if (hasErrorAlready()) {
            return this;
        }

        if (condition.isTrue(fieldValue)) {
            parameterMap.reject(fieldName, message, arguments);
        }

        return this;
    }

    private boolean hasErrorAlready() {
        return parameterMap.hasError(fieldName);
    }

    public String getValue() {
        return this.fieldValue;
    }
}
