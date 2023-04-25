package com.appsdeveloperblog.photoapp.api.users.shared;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateUtil extends DateUtils {

    public static final String DATE_FORMAT = "yyyy-M-d";
    public static final String DATE_TIME_FORMAT = "yyyy-M-d H:m:s";
    public static final String DATE_DAY_TIME_FORMAT = "EEEE, dd MMMM yyyy HH:mm";
    public static final String TO_DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isSameDay(OffsetDateTime date1, OffsetDateTime date2) {
        return date1.truncatedTo(ChronoUnit.DAYS).equals(date2.truncatedTo(ChronoUnit.DAYS));
    }

    public static LocalDate toDate(String date) {
        try {
            return LocalDate.parse(date, ofPattern(DATE_FORMAT));
        } catch (NullPointerException | DateTimeParseException e) {
            return null;
        }
    }

    public static OffsetDateTime toDateTime(String dateTime) {
        try {
            return LocalDateTime
                    .parse(dateTime, ofPattern(DATE_TIME_FORMAT))
                    .atOffset(offset());
        } catch (NullPointerException | DateTimeParseException e) {
            return null;
        }
    }

    public static OffsetDateTime toDateTime(String dateTime, String datePattern) {
        try {
            return LocalDateTime
                    .parse(dateTime, ofPattern(datePattern))
                    .atZone(ZoneId.systemDefault()).toOffsetDateTime();
        } catch (NullPointerException | DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDate convertToLocalDate(String dateTime, String datePattern) {
        try {
            return LocalDate
                    .parse(dateTime, ofPattern(datePattern));
        } catch (NullPointerException | DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDateTime convertToLocalDateTime(String dateTime, String datePattern) {
        try {
            return LocalDateTime
                    .parse(dateTime, ofPattern(datePattern));
        } catch (NullPointerException | DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static OffsetDateTime toDateTime(long epochSeconds) {
        return OffsetDateTime
                .ofInstant(Instant.ofEpochSecond(epochSeconds, 0), offset());
    }

    public static ZoneOffset offset() {
        return OffsetDateTime.now().getOffset();
    }


    public static String toDateDayTime(Instant updatedAt){
        try{
            return DateTimeFormatter.ofPattern(DATE_DAY_TIME_FORMAT)
                    .withZone(ZoneId.of("Asia/Manila"))
                    .format(updatedAt);
        }catch (NullPointerException | DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}