package org.jala.university;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.services.CreditCardImpl;
import org.jala.university.services.CreditCardModule;
import org.jala.university.presentation.InterfazView;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CreditCardModule creditCardModule = new CreditCardImpl(new CreditCardDao(entityManager));



        SwingUtilities.invokeLater(() -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

//            CreditCardView creditCardFormUI = new CreditCardView(creditCardModule, entityManager);
//            creditCardFormUI.setVisible(true);
            InterfazView interfazUI = new InterfazView();

            transaction.commit();
        });
    }
}
