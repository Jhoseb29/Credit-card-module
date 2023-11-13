package org.jala.university.presentation;

import org.jala.university.model.CreditCardModel;
import org.jala.university.services.RecordImpl;
import org.jala.university.controllers.ControllerRecordCard;
import org.jala.university.utilities.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationCreditCardView {
    private JFrame frame;
    private final CreditCardModel creditCardModel;
    private ControllerRecordCard controllerRecordCard; // Agregado
    private RecordImpl record; // Agregado


    public InformationCreditCardView(CreditCardModel creditCardModel, ControllerRecordCard controllerRecordCard, RecordImpl record) {
        this.creditCardModel = creditCardModel;
        this.controllerRecordCard = controllerRecordCard; // Agregado
        this.record = record; // Agregado

        frame = new JFrame("Credit Card");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JButton btnStatus = new JButton("See Status");
        JButton btnBalance = new JButton("See Balance");
        JButton btnBalanceLimit = new JButton("See Balance Limit");
        JButton btnExpirationDate = new JButton("See Expiration Date");
        JButton btnCardManagement = new JButton("Card management");
        JButton btnGeneratePin = new JButton("PIN");
        JButton btnActionsCard = new JButton("ACTIONS CARD");

        frame.add(btnStatus);
        frame.add(btnBalance);
        frame.add(btnBalanceLimit);
        frame.add(btnExpirationDate);
        frame.add(btnCardManagement);
        frame.add(btnGeneratePin);
        frame.add(btnActionsCard);


        btnStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String status = String.valueOf(creditCardModel.getStatus());
                Dialog.getInformation("Status:" + status);
            }
        });

        btnBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float balance = (float) creditCardModel.getCurrent_limit();
               Dialog.getInformation("Balance: " + balance);
            }
        });

        btnBalanceLimit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float creditLimit = (float) creditCardModel.getCredit_limit();
                Dialog.getInformation("Credit Limit: " + creditLimit);
            }
        });

        btnExpirationDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int expirationMonth = creditCardModel.getExpiration_month();
                int expirationYear = creditCardModel.getExpiration_year();
                Dialog.getInformation("Expiration Date: " + expirationMonth + "/" + expirationYear);
            }
        });

        btnCardManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreditCardBlockView cardManagent = new CreditCardBlockView();
                cardManagent.setVisible(true);
            }
        });

        btnGeneratePin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomPinGeneratorView pinGeneratorView = new RandomPinGeneratorView();
                pinGeneratorView.setVisible(true);
            }
        });

        btnActionsCard.addActionListener(event -> {
            // Crear una instancia de CreditCardActionsView

            CreditCardActionsView creditCardActionsView = new CreditCardActionsView(controllerRecordCard, record);

            // Hacer visible la nueva vista
            creditCardActionsView.setVisible(true);
        });

        frame.setVisible(true);
    }

}
