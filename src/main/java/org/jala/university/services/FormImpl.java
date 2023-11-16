package org.jala.university.services;

import org.jala.university.model.FormModel;
import org.jala.university.dao.FormDao;

import java.util.List;
import java.util.UUID;

public class FormImpl implements FormModule {
    private final FormDao formDao;

    public FormImpl(FormDao formDao) {
        this.formDao = formDao;
    }

    @Override
    public void create(FormModel creditCard) {
        formDao.create(creditCard);
    }

    @Override
    public FormModel get(UUID id) {
        return formDao.findOne(id);
    }

    @Override
    public List<FormModel> getAll() {
        return formDao.findAll();
    }

    @Override
    public FormModel update(FormModel creditCard) {
        return formDao.update(creditCard);
    }

    @Override
    public void delete(UUID id) {
        formDao.deleteById(id);
    }

    public int checkCard() throws Exception{
        try{
            //int count = FormRepository.checkCard();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return 0;
    }


}
