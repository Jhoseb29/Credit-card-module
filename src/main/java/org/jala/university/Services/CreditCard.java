package org.jala.university.Services;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CreditCard extends JFrame {
  private final Map<String, Boolean> creditCards;

  private JTextField cardNumberField;
  private JTextArea resultArea;

  public CreditCard() {
    creditCards = new HashMap<>();

    setTitle("Credit Card Management");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 200);

    JPanel panel = new JPanel();
    JLabel cardNumberLabel = new JLabel("Card Number:");
    cardNumberField = new JTextField(10);
    JButton blockButton = new JButton("Block");
    resultArea = new JTextArea(5, 20);
    resultArea.setEditable(false);

    panel.add(cardNumberLabel);
    panel.add(cardNumberField);
    panel.add(blockButton);
    panel.add(resultArea);

    blockButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String cardNumber = cardNumberField.getText();
        if (creditCards.containsKey(cardNumber)) {
          creditCards.put(cardNumber, true);
          resultArea.setText("Card blocked.");
        } else {
          resultArea.setText("Card not found.");
        }
      }
    });

    add(panel);
    setVisible(true);
  }
}