package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.domain.CreditCardImpl;
import org.jala.university.domain.CreditCardModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.*;

public class ButtonSolitationForm extends JFrame {

    public ButtonSolitationForm() {
        setTitle("Button Credit Card");
        setSize(400, 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        // Crear un botón para abrir el formulario de tarjeta de crédito
        JButton openCreditCardButton = new JButton("Solicitud de tarjeta de credito");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CreditCardModule creditCardModule = new CreditCardImpl(new CreditCardDao(entityManager));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        openCreditCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se hace clic en el botón, abrir el formulario de tarjeta de crédito
                CreditCardView creditCardFormUI = new CreditCardView(creditCardModule, entityManager);
                creditCardFormUI.setVisible(true);
            }
        });

        // Agregar el botón a la vista del botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openCreditCardButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
