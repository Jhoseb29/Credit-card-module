package org.jala.university.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import org.jala.university.model.CreditCardModel;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerCreditCardTest {
    CreditCardModel creditCardModel;
    ControllerCreditCard controllerCreditCard;
    EntityManager entityManager;
    CreditCardModule creditCardModule;
    RecordImpl record;
    EntityTransaction transactionMock;
    @BeforeEach
    public void setup(){

        creditCardModel = CreditCardModel.builder().build();
        entityManager = mock(EntityManager.class);
        transactionMock = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transactionMock);
        creditCardModule = mock(CreditCardModule.class);
        record = mock(RecordImpl.class);
        controllerCreditCard = new ControllerCreditCard(creditCardModel, entityManager, creditCardModule, record );
    }
    @Test
    @Transactional
    void withdrawCashTest(){
        int balance = 3000;  // tu puedes poner tu saldo actual
        creditCardModel.setCurrent_limit(balance);
        int mount = 500; // tu puedes poner el monto que deseas retirar
        int statusCard = 1;
        creditCardModel.setStatus(statusCard);
        int currentBalance = controllerCreditCard.withdrawCash(mount);
        int expectedBalance = balance - (int) (mount + mount * 0.05);
        assertEquals(expectedBalance, currentBalance);
        System.out.println(expectedBalance);
        System.out.println(creditCardModel.getCurrent_limit());

    }
}
