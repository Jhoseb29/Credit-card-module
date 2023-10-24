package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import org.jala.university.CreditCardForm;

import java.util.UUID;

public class CreditCardDao extends AbstractDAO<CreditCardForm, UUID>{
    public CreditCardDao(EntityManager entityManager) {
        super(UUID.class, CreditCardForm.class, entityManager);
    }
}
