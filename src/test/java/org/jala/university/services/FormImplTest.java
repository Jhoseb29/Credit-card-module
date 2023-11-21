package org.jala.university.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.dao.FormDao;
import org.jala.university.model.FormModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class FormImplTest {
    FormModel formModel;
    FormDao formDao;
    FormModule formModule;
    FormImpl formImpl;
    EntityManager entityManager;
    EntityTransaction transactionMock;
    @BeforeEach
    public void setup(){
        formModel = FormModel.builder().build();
        entityManager = mock(EntityManager.class);
        transactionMock =  mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transactionMock);
        formDao = mock(FormDao.class);
        formModule = new FormImpl(formDao);
        formImpl = new FormImpl(formDao);
    }
    @Test
    public void createCreditCardFormTest(){
        when(formDao.create(any(FormModel.class))).thenReturn(formModel);
        FormModel newFormModel =  formDao.create(formModel);
        assertEquals(formModel, newFormModel);
        verify(formDao, times(1)).create(formModel);

    }
    @Test
    public void deleteByIdCreditCardFormTest(){
        doNothing().when(formDao).deleteById(formModel.getId());
        formDao.deleteById(formModel.getId());
        verify(formDao, times(1)).deleteById(formModel.getId());
        when(formDao.findOne(formModel.getId())).thenReturn(null);
        assertNull(formModule.get(formModel.getId()));
    }
    @Test
    public void getFormById(){
        UUID formId = formModel.getId();
        when(formDao.findOne(formId)).thenReturn(formModel);
        FormModel currentFormModel = formModule.get(formId);
        verify(formDao, times(1)).findOne(formId);
        assertEquals(formModel, currentFormModel);


    }
}

