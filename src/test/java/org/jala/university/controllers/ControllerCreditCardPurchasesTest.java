package org.jala.university.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.model.CreditCardModel;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerCreditCardPurchasesTest {

  CreditCardModel creditCardModel;
  ControllerCreditCard controllerCreditCard;
  CreditCardModule creditCardModule;
  RecordImpl record;
  EntityManager entityManager;

  @BeforeEach
  public void setup() {
    creditCardModel = CreditCardModel.builder().build();
    entityManager = mock(EntityManager.class);
    creditCardModule = mock(CreditCardModule.class);
    record = mock(RecordImpl.class);
    controllerCreditCard = new ControllerCreditCard(creditCardModel, entityManager, creditCardModule, record);
  }

  @Test
  void payTestSufficientFunds() {
    // Arrange
    int initialBalance = 1000;
    int paymentAmount = 500;

    creditCardModel.setCurrent_limit(initialBalance);

    // Configura la instancia de EntityTransaction para que no sea null
    EntityTransaction transaction = mock(EntityTransaction.class);
    when(entityManager.getTransaction()).thenReturn(transaction);

    // Act
    int newBalance = controllerCreditCard.pay(paymentAmount);

    // Assert
    int expectedBalance = initialBalance - paymentAmount;
    assertEquals(expectedBalance, newBalance);
  }
}

  /*@Test
  void payTestSufficientFunds() {
    int initialBalance = 1000;
    int paymentAmount = 500;

    creditCardModel.setCurrent_limit(initialBalance);

    int newBalance = controllerCreditCard.pay(paymentAmount);

    int expectedBalance = initialBalance - paymentAmount;
    assertEquals(expectedBalance, newBalance);
  }*/