package org.jala.university.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
    private static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";
    private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(PHONE_NUMBER_PATTERN);
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_REGEX = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValidEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidIncome(double income) {
        return income >= 0;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_NUMBER_REGEX.matcher(phoneNumber).matches();
    }

    public static boolean isValidBirthdate(String birthdate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(birthdate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_REGEX.matcher(password).matches();
    }
}