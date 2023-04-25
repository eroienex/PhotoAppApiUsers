package com.appsdeveloperblog.photoapp.api.users.shared;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public abstract class Validator<T> {

    public T validate(ParameterMap body) {
        T result = doValidate(body);

        if (body.hasErrors()) {
            Map<String, String> errors = new HashMap<>(body.getErrors());

            //throw new ValidationException(errors);
        }

        return result;
    }

    protected abstract T doValidate(ParameterMap body);

    public static boolean isEmpty(Object obj) {
        return StringUtils.isEmpty(obj);
    }

    public static boolean isAlpha(String s) {
        return s.matches("[a-zA-Z]+");
    }

    public static boolean isAlphaWithSpace(String s) {
        return s.matches("[ a-zA-Z]+");
    }

    public static boolean isFirstName(String s) {
        return s.matches("[ a-zA-Z0-9ñÑ,.-]+");
    }

    public static boolean isLastName(String s) {
        return s.matches("[ a-zA-Z0-9ñÑ,.-]+");
    }

    public static boolean isAlphaNumeric(String s) {
        return s.matches("[a-zA-Z0-9]+");
    }

    public static boolean isAlphaNumericWithSpecialCharactersAndSpace(String s) {
        return s.matches("[ a-zA-Z0-9ñÑ!@#$%^&*()_,./-]+");
    }

    public static boolean isEmail(String email) {
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public static boolean isMobile(String mobile) {
        return isNumber(mobile);
    }

    public static boolean isNumber(String string) {
        boolean isLong;
        boolean isBigDecimal;

        try {
            Long.parseLong(string);

            isLong = true;
        } catch (NumberFormatException e) {
            isLong = false;
        }

        try {
            new BigDecimal(string);

            isBigDecimal = true;
        } catch (NumberFormatException e) {
            isBigDecimal = false;
        }

        return isLong || isBigDecimal;
    }

    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDate(String date) {
        return DateUtil.toDate(date) != null;
    }

    public static boolean isGender(String gender) {
        return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F");
    }

    /**
     * Validate each object inside the list. The list should only contain string values
     *
     * @param list ArrayList to be validated
     */
    public void validateStringList(ArrayList list) {
        // Descending loop to avoid errors when removing the object
        for (int i = list.size() - 1; i > 0; i--) {
            Object value = list.get(i);

            if (value == null) {
                list.remove(i);

                continue;
            }

            if (isNumber(String.valueOf(value))) {
                list.remove(i);

                continue;
            }

            if (!(value instanceof String)) {
                list.remove(i);

                continue;
            }

            // Remove list duplicates
            removeDuplicates(list, value, i);
        }
    }

    /**
     * Remove duplicates from an array list
     *
     * @param list      List to be validated
     * @param value     The value to be checked for duplicates
     * @param index     Current index being checked
     */
    public void removeDuplicates(ArrayList list, Object value, int index) {
        // Descending loop to avoid errors when removing the value
        // index - 1 to skip validation on the current value that is
        // being checked for duplicates
        for (int i = index - 1; i > 0; i--) {
            // Check if the value in the list matches any of the list
            // being checked
            if (String.valueOf(list.get(i)).equalsIgnoreCase(String.valueOf(value))) {
                list.remove(i);
            }
        }
    }
}