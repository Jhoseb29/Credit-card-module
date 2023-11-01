package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jala.university.model.CreditCardTable;

import java.util.UUID;

public class CreditCardTableDao extends AbstractDAO<CreditCardTable, UUID> {

    public CreditCardTableDao(EntityManager entityManager) {
        super(UUID.class, CreditCardTable.class, entityManager);
    }
    @Transactional
    public float getCurrentLimit(UUID id) {
        CreditCardTable creditCard = findOne(id);
        return creditCard != null ? creditCard.getCurrent_limit() : 0.0f;
    }
    @Transactional
    public float calculateCreditLimit(UUID id, double userIncome) {
        CreditCardTable creditCard = findOne(id);
        if (creditCard != null) {
            float creditLimit = (float) (userIncome * 1.5);
            creditCard.setCredit_limit(creditLimit);
            update(creditCard);
            return creditLimit;
        }
        return 0.0f;
    }
    @Transactional
    public String getAccountStatus(UUID id) {
        CreditCardTable creditCard = findOne(id);
        if (creditCard != null) {
            int status = creditCard.getStatus();
            return (status == 1) ? "active" : "blocked up";
        }
        return "Account not found";
    }
    @Transactional
    public boolean isBalanceZero(UUID id) {
        CreditCardTable creditCard = findOne(id);
        if (creditCard != null) {
            return creditCard.getCurrent_limit() == 0.0f;
        }
        return false;
    }



}
