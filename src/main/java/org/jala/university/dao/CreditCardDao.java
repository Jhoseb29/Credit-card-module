package org.jala.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.jala.university.model.CreditCardModel;

import java.util.List;
import java.util.UUID;

public class CreditCardDao extends AbstractDAO<CreditCardModel, UUID> {

    public CreditCardDao(EntityManager entityManager) {
        super(UUID.class, CreditCardModel.class, entityManager);
    }

    @Transactional
    public List<CreditCardModel> findAll() {
        return entityManager.createQuery("SELECT r FROM credit_card r", CreditCardModel.class)
                .getResultList();
    }

    @Transactional
    public CreditCardModel findByCardNumber(String card) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CreditCardModel> query = em.createQuery(
                    "SELECT c FROM CreditCardModel c WHERE c.cardNumber = :cardNumber", CreditCardModel.class);
            query.setParameter("cardNumber", card);
            List<CreditCardModel> resultList = query.getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        } finally {
            em.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
