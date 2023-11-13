package org.jala.university.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {

  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
  private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
  private static final String PHONE_NUMBER_PATTERN = "^[0-9]{7,11}$";
  private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(PHONE_NUMBER_PATTERN);

  public static boolean isValidEmail(String email) {
    return EMAIL_REGEX.matcher(email).matches();
  }

  public static boolean isValidIncome(double income) {
    return income >= 3000;
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    return PHONE_NUMBER_REGEX.matcher(phoneNumber).matches();
  }

  public static boolean isValidBirthdate(String birthdate) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date parsedDate = dateFormat.parse(birthdate);

      // Obtener la fecha actual
      Date currentDate = new Date();

      // Calcular la edad en años
      long ageInMillis = currentDate.getTime() - parsedDate.getTime();
      long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

      // Validar que la persona tiene al menos 18 años
      return ageInYears >= 18;
    } catch (ParseException e) {
      return false;
    }
  }
}