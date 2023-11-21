package org.jala.university.presentation;
import jakarta.persistence.*;
import org.jala.university.controllers.ControllerCreditCard;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.dao.RecordDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.services.CreditCardImpl;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import static org.jala.university.utilities.Querys.validateCreditCard;


public class InterfazView extends  JFrame {

    private JFrame frame;
    private EntityManager entityManager;

    public InterfazView(EntityManagerFactory entityManagerFactory) {

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

//                if( validateCreditCard().getResultList().size() == 0){
                    CreditCardView creditCardFormUI = new CreditCardView();
                    creditCardFormUI.setVisible(true);
//                }else {
//
//                    entityManager = entityManagerFactory.createEntityManager();
//                    EntityTransaction transaction = entityManager.getTransaction();
//                    RecordImpl record = new RecordImpl(new RecordDao(UUID.class, RecordModel.class, entityManager));
//                    CreditCardModel creditCardModel = null;
//                    CreditCardModule creditCardTableModule = new CreditCardImpl(new CreditCardDao(entityManager));
//                    creditCardTableModule.create(null);
//                    ControllerCreditCard controllerRecordCard = new ControllerCreditCard(creditCardModel, entityManager, creditCardTableModule, record );
//                    SwingUtilities.invokeLater(() -> new InformationCreditCardView(creditCardModel, controllerRecordCard, record));
//                }

            }
        });
    }
}