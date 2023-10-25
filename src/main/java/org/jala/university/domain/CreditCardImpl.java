package org.jala.university.domain;

import org.jala.university.model.CreditCardForm;
import org.jala.university.dao.CreditCardDao;

import java.util.List;
import java.util.UUID;

public class CreditCardImpl implements CreditCardModule{
    private final CreditCardDao creditCardDao;

    public CreditCardImpl(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    @Override
    public void create(CreditCardForm creditCard) {
        creditCardDao.create(creditCard);
    }

    @Override
    public CreditCardForm get(UUID id) {
        return creditCardDao.findOne(id);
    }

    @Override
    public List<CreditCardForm> getAll() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCardForm update(CreditCardForm creditCard) {
        return creditCardDao.update(creditCard);
    }

    @Override
    public void delete(UUID id) {
        creditCardDao.deleteById(id);

    }
}
