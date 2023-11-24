package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.domain.CreditCardImpl;
import org.jala.university.domain.CreditCardModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonSolitationForm extends JButton {
    JFrame frame = new JFrame();

    public ButtonSolitationForm() {
        setText("Solicitud de tarjeta de credito");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CreditCardModule creditCardModule = new CreditCardImpl(new CreditCardDao(entityManager));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se hace clic en el botón, abrir el formulario de tarjeta de crédito
                CreditCardView creditCardFormUI = new CreditCardView(creditCardModule, entityManager);
                creditCardFormUI.setVisible(true);
                frame.setEnabled(false);
                creditCardFormUI.setFrameCardView(frame);
            }
        });
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
}
