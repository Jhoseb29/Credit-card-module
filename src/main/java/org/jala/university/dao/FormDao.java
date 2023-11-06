package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import org.jala.university.model.FormModel;

import java.util.UUID;

public class FormDao extends AbstractDAO<FormModel, UUID>{
    public FormDao(EntityManager entityManager) {
        super(UUID.class, FormModel.class, entityManager);
    }
}
