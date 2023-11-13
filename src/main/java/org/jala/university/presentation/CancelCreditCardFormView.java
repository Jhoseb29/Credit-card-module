package org.jala.university.presentation;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import org.jala.university.Services.CreditCardModule;

public class CancelCreditCardFormView extends JDialog {
    private final CreditCardModule creditCardModule;
    private final String cardNumber;

    public CancelCreditCardFormView(CreditCardModule creditCardModule, String cardNumber) {
        this.creditCardModule = creditCardModule;
        this.cardNumber = cardNumber;
        setTitle("Cancel Credit Card");
        setSize(300, 150);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel confirmationLabel = new JLabel("Are you sure you want to cancel your credit card?");
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(confirmationLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    creditCardModule.cancelCreditCardByNumber(cardNumber);

                    JOptionPane.showMessageDialog(null, "Credit card successfully canceled.");
                } catch (Exception ex) {

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error canceling credit card: " + ex.getMessage());
                }


                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el formulario si el usuario decide cancelar la operación
                dispose();
            }
        });
    }
}
