package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jala.university.model.FormModel;
import org.jala.university.model.RecordModel;

import java.util.UUID;

public class FormDao extends AbstractDAO<FormModel, UUID>{
    public FormDao(EntityManager entityManager) {
        super(UUID.class, FormModel.class, entityManager);
    }
    @Transactional
    public FormModel create(FormModel entity) {
        entityManager.persist(entity);
        return entity;
    }
}
