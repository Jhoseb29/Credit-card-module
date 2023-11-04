package org.jala.university.services;

import org.jala.university.model.FormModel;
import org.jala.university.dao.CreditCardDao;

import java.util.List;
import java.util.UUID;

public class CreditCardImpl implements CreditCardModule{
    private final CreditCardDao creditCardDao;

    public CreditCardImpl(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    @Override
    public void create(FormModel creditCard) {
        creditCardDao.create(creditCard);
    }

    @Override
    public FormModel get(UUID id) {
        return creditCardDao.findOne(id);
    }

    @Override
    public List<FormModel> getAll() {
        return creditCardDao.findAll();
    }

    @Override
    public FormModel update(FormModel creditCard) {
        return creditCardDao.update(creditCard);
    }

    @Override
    public void delete(UUID id) {
        creditCardDao.deleteById(id);

    }

}
