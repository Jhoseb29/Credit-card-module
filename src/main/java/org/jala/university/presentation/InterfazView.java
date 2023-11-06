package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.dao.FormDao;
import org.jala.university.services.CreditCardImpl;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.FormImpl;
import org.jala.university.services.FormModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfazView extends  JFrame {

    private JFrame frame;

    public InterfazView() {

        frame = new JFrame("Interfaz");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JButton btnStatus = new JButton("Credit Card");

        frame.add(btnStatus);

        frame.setVisible(true);
        btnStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                FormModule creditCardModule = new FormImpl(new FormDao(entityManager));

                SwingUtilities.invokeLater(() -> {
                    EntityTransaction transaction = entityManager.getTransaction();
                    transaction.begin();

                    CreditCardView creditCardFormUI = new CreditCardView(creditCardModule, entityManager);
                    creditCardFormUI.setVisible(true);
                    transaction.commit();
                });
            }
        });
    }
}