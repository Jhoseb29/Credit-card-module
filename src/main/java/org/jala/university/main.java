package org.jala.university;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.FormDao;
import org.jala.university.presentation.CreditCardView;
import org.jala.university.services.FormImpl;
import org.jala.university.services.FormModule;
import org.jala.university.presentation.InterfazView;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        FormModule creditCardModule = new FormImpl(new FormDao(entityManager));



        SwingUtilities.invokeLater(() -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            CreditCardView creditCardFormUI = new CreditCardView(creditCardModule, entityManager);
            creditCardFormUI.setVisible(true);

            FormImpl form = new FormImpl(new FormDao(entityManager));
            try {
                System.out.println("RESULTADO: " + form.checkCard());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            InterfazView interfazUI = new InterfazView();

            transaction.commit();
        });
    }
}
