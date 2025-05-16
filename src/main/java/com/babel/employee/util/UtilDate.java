package com.babel.employee.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilDate {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static LocalDate convertStringToLocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(stringDate, formatter);
    }

    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(formatter);
    }
}
