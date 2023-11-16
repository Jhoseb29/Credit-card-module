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
    return email != null && EMAIL_REGEX.matcher(email).matches();
  }

  public static boolean isValidIncome(double income) {
    return income >= 300;
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber != null && phoneNumber.matches("[0-9]{7,11}");
  }

  public static boolean isValidBirthdate(String birthdate) {
    if (birthdate == null || birthdate.isEmpty()) {
      return false; // Fecha nula o vacía es inválida
    }

    SimpleDateFormat[] dateFormats = {
        new SimpleDateFormat("MM/dd/yyyy"),
        new SimpleDateFormat("dd/MM/yyyy"),
        new SimpleDateFormat("yyyy/MM/dd"),
        // Agrega otros formatos aquí según sea necesario
    };

    for (SimpleDateFormat dateFormat : dateFormats) {
      try {
        dateFormat.setLenient(false); // Desactiva la flexibilidad en el análisis
        Date parsedDate = dateFormat.parse(birthdate);

        // Obtener la fecha actual
        Date currentDate = new Date();

        // Calcular la edad en años
        long ageInMillis = currentDate.getTime() - parsedDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

        // Validar que la persona tiene al menos 18 años
        if (ageInYears >= 18) {
          return true; // Si la edad es válida, entonces la fecha también es válida
        }
      } catch (ParseException ignored) {
        // Ignorar ParseException, ya que estamos probando diferentes formatos
      }
    }

    return false; // Si no coincide con ninguno de los formatos o la edad no es válida, es inválida
  }
  public static boolean isValidWithdrawal(int withdrawalAmount, int currentBalance) {
    return withdrawalAmount > 0 && withdrawalAmount <= currentBalance;
  }
}