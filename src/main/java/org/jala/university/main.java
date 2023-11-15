package org.jala.university;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.presentation.InterfazView;
import org.jala.university.services.CreditCardImpl;

import javax.swing.*;

public class main {
    private static EntityManager entityManager;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {



            InterfazView interfazUI = new InterfazView();

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
            EntityManager em = entityManagerFactory.createEntityManager();
            String sql = "SELECT COUNT(*) FROM credit_card";
            Query query = em.createQuery(sql);

            System.out.println(query.getResultList());





        });
    }
}
