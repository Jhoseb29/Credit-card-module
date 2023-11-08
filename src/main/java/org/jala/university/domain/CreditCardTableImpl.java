package org.jala.university.domain;

import org.jala.university.dao.CreditCardTableDao;
import org.jala.university.model.CreditCardTable;

import java.util.List;
import java.util.UUID;

public class CreditCardTableImpl implements CreditCardTableModule {
    private final CreditCardTableDao creditCardDao;

    public CreditCardTableImpl(CreditCardTableDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    @Override
    public void create(CreditCardTable creditCard) {
        creditCardDao.create(creditCard);

    }
    @Override
    public CreditCardTable get(UUID id) {
        return creditCardDao.findOne(id);
    }

    @Override
    public List<CreditCardTable> getAll() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCardTable update(CreditCardTable creditCard) {
        return creditCardDao.update(creditCard);
    }

    @Override
    public void delete(UUID id) {
        creditCardDao.deleteById(id);
    }

    @Override
    public float getCurrentLimit(UUID id) {
        return creditCardDao.getCurrentLimit(id);
    }

    @Override
    public float getCredit_limit(UUID id, double income) {
        return creditCardDao.calculateCreditLimit(id, income);
    }

    @Override
    public String getAccountStatus(UUID id) {
        return creditCardDao.getAccountStatus(id);
    }


}
