package org.jala.university.presentation;

import org.jala.university.controllers.ControllerCreditCard;
import org.jala.university.model.CreditCardModel;
import org.jala.university.services.RecordImpl;
import org.jala.university.utilities.CreditCardUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomPinGeneratorView extends JFrame {

    private final CreditCardModel creditCardModel;
    private final JLabel pinLabel;
    private final JButton generateButton;
    private final JButton nextBtn;
    private ControllerCreditCard controllerRecordCard;
    private RecordImpl record;

    public RandomPinGeneratorView(CreditCardModel creditCardModel, ControllerCreditCard controllerRecordCard, RecordImpl record) {
        this.creditCardModel = creditCardModel;
        setTitle("PIN");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pinLabel = new JLabel("", JLabel.CENTER);
        generateButton = new JButton("Generate PIN");
        nextBtn = new JButton("Next");


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pin = CreditCardUtilities.generateRandomPIN();
                pinLabel.setText("Pin Aleatory: " + pin);
                creditCardModel.setNIP(pin);

            }
        });
        nextBtn.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> new InformationCreditCardView(creditCardModel, controllerRecordCard, record));

        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(nextBtn);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(pinLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

}
