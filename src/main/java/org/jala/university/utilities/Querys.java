package org.jala.university.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Querys {
    public static Query validateCreditCard(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager em = entityManagerFactory.createEntityManager();
        String sql = "SELECT COUNT(*) FROM credit_card";
        return em.createQuery(sql);
    }
}
