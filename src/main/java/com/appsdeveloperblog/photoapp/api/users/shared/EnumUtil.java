package com.appsdeveloperblog.photoapp.api.users.shared;

public class EnumUtil {

    public static <T extends Enum<?>> T searchEnum(Class<T> enumeration, String search) {
        if (search == null) {
            return null;
        }

        for (T each : enumeration.getEnumConstants()) {
            if (each.name().compareToIgnoreCase(search) == 0) {
                return each;
            }
        }
        return null;
    }
}