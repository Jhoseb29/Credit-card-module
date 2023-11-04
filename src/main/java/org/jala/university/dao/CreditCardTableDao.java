package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jala.university.model.CreditCardModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CreditCardTableDao extends AbstractDAO<CreditCardModel, UUID> {

    public CreditCardTableDao(EntityManager entityManager) {
        super(UUID.class, CreditCardModel.class, entityManager);
    }
    @Transactional
    public double getCurrentLimit(UUID id) {
        CreditCardModel creditCard = findOne(id);
        return creditCard != null ? creditCard.getCurrent_limit() : 0.0f;
    }
    @Transactional
    public float calculateCreditLimit(UUID id, double userIncome) {
        CreditCardModel creditCard = findOne(id);
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
        CreditCardModel creditCard = findOne(id);
        if (creditCard != null) {
            int status = creditCard.getStatus();
            return (status == 1) ? "active" : "blocked up";
        }
        return "Account not found";
    }
    @Transactional
    public String getExpirationDate(UUID id) {
        CreditCardModel creditCard = findOne(id);
        if (creditCard != null) {
            Calendar calendar = Calendar.getInstance();
            Date currentDate = new Date();
            calendar.setTime(currentDate);
            calendar.add(Calendar.YEAR, 4);
            int expirationMonth = calendar.get(Calendar.MONTH) + 1;
            int expirationYear = calendar.get(Calendar.YEAR);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            return dateFormat.format(calendar.getTime());
        }
        return "Expiration date not available";
    }


}
