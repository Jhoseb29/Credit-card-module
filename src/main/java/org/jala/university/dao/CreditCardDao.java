package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.persistence.TypedQuery;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreditCardDao extends AbstractDAO<CreditCardModel, UUID> {

    public CreditCardDao(EntityManager entityManager) {
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
    @Transactional
    public List<CreditCardModel> findAll() {
        return entityManager.createQuery("SELECT r FROM credit_card r", CreditCardModel.class)
                .getResultList();
    }

    @Transactional
    public CreditCardModel findByCardNumber(String card) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CreditCardModel> query = em.createQuery(
                    "SELECT c FROM CreditCardModel c WHERE c.cardNumber = :cardNumber", CreditCardModel.class);
            query.setParameter("cardNumber", card);
            List<CreditCardModel> resultList = query.getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        } finally {
            em.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
