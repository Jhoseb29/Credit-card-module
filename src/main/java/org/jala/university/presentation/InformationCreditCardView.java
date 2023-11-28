package org.jala.university.presentation;

import org.jala.university.controllers.ControllerCreditCard;
import org.jala.university.model.CreditCardModel;
import org.jala.university.services.RecordImpl;
import org.jala.university.utilities.Dialog;
import org.jala.university.utilities.PaneCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

public class InformationCreditCardView {
    private JFrame frame;
    private final CreditCardModel creditCardModel;
    private ControllerCreditCard controllerCreditCard; // Agregado
    private RecordImpl record; // Agregado

    public InformationCreditCardView(CreditCardModel creditCardModel, ControllerCreditCard controllerRecordCard, RecordImpl record) {
        int WIDTH_BUTTON = 160;
        int HEIGHT_BUTTON = 30;
        this.creditCardModel = creditCardModel;
        this.controllerCreditCard = controllerRecordCard; // Agregado
        this.record = record; // Agregado

        frame = new JFrame("Credit Card");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        JButton btnStatus = new JButton("See Status"); btnStatus.setBounds(20,293,WIDTH_BUTTON,HEIGHT_BUTTON);
        JButton btnBalance = new JButton("See Balance"); btnBalance.setBounds(185,293,WIDTH_BUTTON,HEIGHT_BUTTON);
        JButton btnBalanceLimit = new JButton("See Balance limit");
        btnBalanceLimit.setBounds(350, 293, WIDTH_BUTTON, HEIGHT_BUTTON);
        JButton btnPin = new JButton("See pin");
        btnPin.setBounds(185, 344, WIDTH_BUTTON, HEIGHT_BUTTON);
        JButton btnActionsCard = new JButton("Actions card");
        btnActionsCard.setBounds(350, 344, WIDTH_BUTTON, HEIGHT_BUTTON);
        JButton btnCard = new JButton("Card");
        btnCard.setBounds(20, 344, WIDTH_BUTTON, HEIGHT_BUTTON);

        frame.add(btnStatus);
        frame.add(btnBalance);
        frame.add(btnBalanceLimit);
        frame.add(btnPin);
        frame.add(btnActionsCard);
        frame.add(btnCard);

        PaneCard frontPanel = new PaneCard(new Color(0x666f7f),new Color(0x262d3d),new Color(0x262d3d)); frontPanel.setBounds(10,10,380,180);
        PaneCard backPanel = new PaneCard(new Color(0xe8e6e6),new Color(0xd7d7d7),new Color(0xD7D7D7)); backPanel.setBounds(100,40,380,180);
        showData(frontPanel,backPanel);
        frame.add(frontPanel);
        frame.add(backPanel);

        btnStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int status = creditCardModel.getStatus();
                String message;
                if (status == 1) {
                    message = "Active";
                } else {
                    message = "Inactive";
                }
                Dialog.getInformation("Status:" + message);
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

        btnPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pin = creditCardModel.getNIP();
                Dialog.getInformation("Pin " + pin);
            }
        });

        btnActionsCard.addActionListener(event -> {
            // Crear una instancia de CreditCardActionsView

            CreditCardActionsView creditCardActionsView = new CreditCardActionsView(controllerRecordCard, record, creditCardModel);

            // Hacer visible la nueva vista
            creditCardActionsView.setVisible(true);
        });
        btnCard.addActionListener(event -> {
            String card = creditCardModel.getCard();
            Dialog.getInformation("Card: " + card);
        });

        frame.setVisible(true);
    }

    private void showData(PaneCard frontPanel, PaneCard backPanel) {
        int expirationMonth = creditCardModel.getExpiration_month();
        int expirationYear = creditCardModel.getExpiration_year();
        String cardNumber = creditCardModel.getCard();
        int cvvCard = creditCardModel.getCvv();

        JLabel cardNumberLabel = new JLabel(cardNumber); cardNumberLabel.setFont(new Font("Monospaced", ITALIC,28));
        JLabel expirationLabel = new JLabel(expirationMonth +"/"+ expirationYear); expirationLabel.setFont(new Font("Monospaced", BOLD,18));
        cardNumberLabel.setForeground(Color.WHITE); expirationLabel.setForeground(Color.WHITE);
        JLabel cvvCardLabel = new JLabel(String.valueOf(cvvCard)); cvvCardLabel.setFont(new Font("Monospaced", BOLD,20));
        frontPanel.add(new JLabel(""));
        frontPanel.add(cardNumberLabel);
        frontPanel.add(expirationLabel);

        for (int i = 0; i < 14; i++) {
            backPanel.add(new JLabel(""));
        }
        backPanel.add(cvvCardLabel);
    }
}
