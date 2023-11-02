package org.jala.university.presentation;

import org.jala.university.domain.CreditCardTableModule;
import org.jala.university.model.CreditCardForm;
import org.jala.university.validations.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class InformationCreditCardView {
    private JFrame frame;
    private final CreditCardTableModule creditCardModule;
    private final UUID cardId;
    private final CreditCardForm creditCardForm = CreditCardForm.builder().build();

    public InformationCreditCardView(CreditCardTableModule creditCardModule, UUID cardId) {
        this.creditCardModule = creditCardModule;
        this.cardId = cardId;

        frame = new JFrame("Credit Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JButton btnStatus = new JButton("See Status");
        JButton btnBalance = new JButton("See Balance");
        JButton btnBalanceLimit = new JButton("See Balance Limit");
        JButton btnExpirationDate = new JButton("See Expiration Date");

        frame.add(btnStatus);
        frame.add(btnBalance);
        frame.add(btnBalanceLimit);
        frame.add(btnExpirationDate);


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


        frame.setVisible(true);
    }

}
