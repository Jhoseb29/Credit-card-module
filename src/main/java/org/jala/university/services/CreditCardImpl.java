package org.jala.university.services;

import org.jala.university.dao.CreditCardDao;
import org.jala.university.model.CreditCardModel;


import java.util.List;
import java.util.UUID;

public class CreditCardImpl implements CreditCardModule {
    private final CreditCardDao creditCardDao;

    public CreditCardImpl(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    @Override
    public void create(CreditCardModel creditCard) {
        creditCardDao.create(creditCard);

    }

    @Override
    public CreditCardModel get(UUID id) {
        return creditCardDao.findOne(id);
    }

    @Override
    public List<CreditCardModel> getAll() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCardModel update(CreditCardModel creditCard) {
        return creditCardDao.update(creditCard);
    }

    @Override
    public void delete(UUID id) {
        creditCardDao.deleteById(id);
    }

    @Override
    public float getCurrentLimit(UUID id) {
        return (float) creditCardDao.getCurrentLimit(id);
    }

    @Override
    public float getCredit_limit(UUID id, double income) {
        return creditCardDao.calculateCreditLimit(id, income);
    }

    @Override
    public String getAccountStatus(UUID id) {
        return creditCardDao.getAccountStatus(id);
    }

    @Override
    public String getExpirationDate(UUID id) {
        return creditCardDao.getExpirationDate(id);
    }

    @Override
    public boolean cancelCreditCard(UUID id) {
        return false;
    }


    @Override
    public void cancelCreditCardByNumber(String cardNumber) throws Exception {
        // Obtener la tarjeta de crédito por número de tarjeta
        CreditCardModel creditCard = creditCardDao.findByCardNumber(cardNumber);

        if (creditCard != null && creditCard.getCurrent_limit() > 0) {
            // Validación del saldo de la tarjeta antes de cancelarla
            throw new Exception("Cannot cancel credit card with a positive balance.");
        }

        if (creditCard != null) {
            creditCard.setStatus(2);  // Supongamos que 2 representa el estado de tarjeta cancelada
            creditCard.setCurrent_limit(0.0);
            creditCardDao.update(creditCard);
        } else {
            throw new Exception("Credit card not found.");
        }
    }

}

