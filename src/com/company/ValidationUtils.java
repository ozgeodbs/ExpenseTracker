package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationUtils {

    public static boolean isValidPositiveInt(String input) {
        try {
            int val = Integer.parseInt(input);
            return val > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidAmount(String input) {
        try {
            double amount = Double.parseDouble(input);
            return amount >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidDescription(String input) {
        return input != null && !input.trim().isEmpty() && input.length() <= 255;
    }
}
