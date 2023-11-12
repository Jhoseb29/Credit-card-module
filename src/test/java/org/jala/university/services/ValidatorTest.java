package org.jala.university.services;

import org.jala.university.utilities.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

  @Test
  void isValidEmailTest() {
    assertTrue(Validator.isValidEmail("test@example.com"));
    assertTrue(Validator.isValidEmail("user123@gmail.com"));
    assertFalse(Validator.isValidEmail("invalid-email"));
    assertFalse(Validator.isValidEmail("user@domain"));
    assertFalse(Validator.isValidEmail(null)); // Agregar prueba para null
    assertFalse(Validator.isValidEmail("")); // Agregar prueba para cadena vacía
    assertFalse(Validator.isValidEmail("test@.com")); // Agregar prueba para dominio inválido
    assertFalse(Validator.isValidEmail("test@com.")); // Agregar prueba para dominio inválido
  }

  @Test
  void isValidIncomeTest() {
    assertTrue(Validator.isValidIncome(500));
    assertTrue(Validator.isValidIncome(1000));
    assertFalse(Validator.isValidIncome(200));
    assertFalse(Validator.isValidIncome(0));
    assertFalse(Validator.isValidIncome(-100)); // Agregar prueba para ingreso negativo
  }

  @Test
  void isValidPhoneNumberTest() {
    assertTrue(Validator.isValidPhoneNumber("123456789"));
    assertTrue(Validator.isValidPhoneNumber("9876543210"));
    assertFalse(Validator.isValidPhoneNumber("invalid-number"));
    assertFalse(Validator.isValidPhoneNumber("123"));
    assertFalse(Validator.isValidPhoneNumber(""));
    assertFalse(Validator.isValidPhoneNumber("12a34b5678"));
  }

  @Test
  void isValidBirthdateTest() {
    assertTrue(Validator.isValidBirthdate("01/01/2000"));
    assertTrue(Validator.isValidBirthdate("12/31/2003"));
    assertFalse(Validator.isValidBirthdate("01/01/2010"));
    assertFalse(Validator.isValidBirthdate("invalid-date"));
    assertFalse(Validator.isValidBirthdate(null));
    assertFalse(Validator.isValidBirthdate(""));
    assertFalse(Validator.isValidBirthdate("01-01-2000"));
  }
}
