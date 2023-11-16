package org.jala.university.presentation;

import org.jala.university.model.CreditCardModel;
import org.jala.university.services.RecordImpl;
import org.jala.university.controllers.ControllerCreditCard;
import org.jala.university.utilities.Dialog;
import org.jala.university.utilities.Validator;

import javax.swing.*;
import java.awt.*;

public class CreditCardActionsView extends JFrame {
    private final ControllerCreditCard controllerRecordCard;
    private final RecordImpl record;
    private final CreditCardModel creditCardModel;
    private JPanel topPanel;
    private JPanel btnPanel;

    public CreditCardActionsView(ControllerCreditCard controllerRecordCard, RecordImpl record, CreditCardModel creditCardModel) {
        this.controllerRecordCard = controllerRecordCard;
        this.record = record;
        this.creditCardModel = creditCardModel;
        setTitle("Credit Card Actions");
        setSize(800, 500);
        setBackground(Color.gray);
        setLocationRelativeTo(null);

        topPanel = new JPanel();
        btnPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JButton updateStatusButton = new JButton("UPDATE STATUS");
        JButton updatePinButton = new JButton("UPDATE PIN");
        JButton makePayButton = new JButton("PAY");
        JButton showHistoryButton = new JButton("SHOW HISTORY");
        JButton withdrawCashButton = new JButton("WITHDRAW CASH");

        btnPanel.add(updateStatusButton);
        btnPanel.add(updatePinButton);
        btnPanel.add(makePayButton);
        btnPanel.add(showHistoryButton);
        btnPanel.add(withdrawCashButton);

        updateStatusButton.addActionListener(event -> {
            String [] statusOptions = {"Active", "Inactive"};
            String select = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a new status: ",
                    "Update Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statusOptions,
                    statusOptions[0]
            );
            if (select != null){
                int newStatus = (select.equals("Active")) ? 1 : 0;
                controllerRecordCard.updateStatus(newStatus);
            }
        });
        updatePinButton.addActionListener(event ->{
            String newPinStr= JOptionPane.showInputDialog(this,
                    "Enter a new pin: ");
            if(newPinStr!=null && !newPinStr.isEmpty()){
                try {
                    int newPin = Integer.parseInt(newPinStr);
                    if (newPin >= 1000 && newPin<=9999){
                        controllerRecordCard.updatePin(newPin);
                    }
                    else {
                        Dialog.error("The PIN must be 4 digits long.");
                    }
                }
                catch (NumberFormatException numberFormatException){
                    Dialog.error("Enter a valid numeric value for the PIN");
                }
            }
        });
        makePayButton.addActionListener(event ->{
            String mountStr = JOptionPane.showInputDialog(this, "Enter the mount: ");
            if (mountStr != null && !mountStr.isEmpty()){
                try {
                    int mount = Integer.parseInt(mountStr);
                    if (mount>0){
                        int balance = controllerRecordCard.pay(mount);
                        Dialog.getInformation("Successful payment."  + balance);
                    }
                    else {
                        Dialog.error("Enter a validate mount.");
                    }
                }
                catch (NumberFormatException numberFormatException){
                    Dialog.error("Enter a valid numeric value for the mount");

                }
            }
        });
        showHistoryButton.addActionListener(event->{
            RecordView recordView = new RecordView(record);
            recordView.setVisible(true);

        });
        withdrawCashButton.addActionListener(event->{
            int currentBalance = (int) creditCardModel.getCurrent_limit();
            String mountStr = JOptionPane.showInputDialog(this, "Enter the mount");
            if (mountStr != null && !mountStr.isEmpty()){
                try {
                    int mount = Integer.parseInt(mountStr);
                    if (Validator.isValidWithdrawal(mount, currentBalance)){
                        int balance = controllerRecordCard.withdrawCash(mount);
                        Dialog.getInformation("Successful Retirement" + balance);
                    }
                    else {
                        Dialog.error("Enter a valid numeric value for the mount");
                    }
                }
                catch (NumberFormatException numberFormatException){
                    Dialog.error("Enter a valid numeric value for the mount");
                }
            }

        });

    }

}
