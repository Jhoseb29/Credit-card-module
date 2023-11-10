package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import org.jala.university.model.RecordModel;

import java.util.List;
import java.util.UUID;

public class RecordDao extends AbstractDAO<RecordModel, UUID> {


    public RecordDao(Class<UUID> idClazz, Class<RecordModel> clazzToSet, EntityManager entityManager) {
        super(idClazz, clazzToSet, entityManager);
    }

    public List<RecordModel> findAll() {
        return entityManager.createQuery("SELECT r FROM RecordModel r", RecordModel.class)
                .getResultList();
    }

    public RecordModel create(RecordModel entity) {
        super.create(entity);
        return entity;
    }



}
