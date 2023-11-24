package org.jala.university.services;

import org.jala.university.dao.CreditCardDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.repository.CreditCardRepository;

import java.util.List;
import java.util.UUID;

public class CreditCardImpl implements CreditCardModule {
    private final CreditCardDao creditCardDao;
    private CreditCardRepository creditCardRepository;

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
    public int checkCard() {
        return creditCardRepository.checkCard();
    }

}
