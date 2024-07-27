package com.shareduck.shareduck.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtils {

    private TimeUtils() {
    }

    public static final String VAS_COMMAND_TIME_FORMAT = "yyyyMMdd-HHmmss";
    public static final String UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final long M_SECONDS = 1;
    public static final long SECONDS = 1000 * M_SECONDS;
    public static final long MINUTES = 60 * SECONDS;

    public static LocalDateTime getCurrent() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getCurrentHour() {
        return getCurrent().truncatedTo(ChronoUnit.HOURS);
    }

    public static LocalDateTime getCurrentDay() {
        return getCurrent().truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime getAfterDays(long days, LocalDateTime time) {
        return time.plusDays(days);
    }

    public static LocalDateTime getAfterDays(long days) {
        return getAfterDays(days, getCurrentDay());
    }

    public static LocalDateTime getBeforeDays(long days, LocalDateTime time) {
        return time.minusDays(days);
    }

    public static LocalDateTime getBeforeDays(long days) {
        return getBeforeDays(days, getCurrentDay());
    }

    public static long getRemainDays(LocalDateTime time) {
        LocalDateTime now = getCurrent();
        return ChronoUnit.DAYS.between(now, time);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static DateTimeFormatter getTimeFormat(String formatStr) {
        return DateTimeFormatter.ofPattern(formatStr);
    }

    public static DateTimeFormatter getVasCommandTimeFormat() {
        return getTimeFormat(VAS_COMMAND_TIME_FORMAT);
    }
}
