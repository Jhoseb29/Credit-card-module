package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jala.university.model.RecordModel;

import java.util.List;
import java.util.UUID;

public class RecordDao extends AbstractDAO<RecordModel, UUID> {


    public RecordDao(Class<UUID> idClazz, Class<RecordModel> clazzToSet, EntityManager entityManager) {
        super(idClazz, clazzToSet, entityManager);
    }
    @Transactional

    public List<RecordModel> findAll() {
        return entityManager.createQuery("SELECT r FROM record r", RecordModel.class)
                .getResultList();
    }

    @Transactional
    public RecordModel create(RecordModel entity) {
        entityManager.persist(entity);
        return entity;
    }



}
