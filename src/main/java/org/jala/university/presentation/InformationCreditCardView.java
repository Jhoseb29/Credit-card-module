package org.jala.university.presentation;

import org.jala.university.services.CreditCardModule;
import org.jala.university.model.FormModel;
import org.jala.university.utilities.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class InformationCreditCardView {
    private JFrame frame;
    private final CreditCardModule creditCardModule;
    private final UUID cardId;
    private final FormModel creditCardForm;


    public InformationCreditCardView(CreditCardModule creditCardModule, UUID cardId, FormModel creditCardForm) {
        this.creditCardModule = creditCardModule;
        this.cardId = cardId;
        this.creditCardForm = creditCardForm;

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
        JButton btnActionsCard = new JButton("ACTIONS CARD");

        frame.add(btnStatus);
        frame.add(btnBalance);
        frame.add(btnBalanceLimit);
        frame.add(btnExpirationDate);
        frame.add(btnCardManagement);
        frame.add(btnActionsCard);


        btnStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String status = creditCardModule.getAccountStatus(cardId);
                Dialog.getInformation("Status:" + status);
            }
        });

        btnBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float balance = creditCardModule.getCurrentLimit(cardId);
               Dialog.getInformation("Balance: " + balance);
            }
        });

        btnBalanceLimit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float creditLimit = creditCardModule.getCredit_limit(cardId, creditCardForm.getIncome());
                Dialog.getInformation("Credit Limit: " + creditLimit);
            }
        });

        btnExpirationDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String expirationDate = creditCardModule.getExpirationDate(cardId);
                if (!expirationDate.equals("Expiration date not available")) {
                    Dialog.getInformation("Expiration Date: " + expirationDate);
                } else {
                    Dialog.getInformation("Expiration date not available");
                }
            }
        });

        btnCardManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreditCardBlockView cardManagent = new CreditCardBlockView();
                cardManagent.setVisible(true);
            }
        });
        btnActionsCard.addActionListener(event->{


        });

        frame.setVisible(true);
    }

}
