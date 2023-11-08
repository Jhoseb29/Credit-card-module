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
    JButton unlockButton = new JButton("Unlock");
    resultArea = new JTextArea(5, 20);
    resultArea.setEditable(false);

    panel.add(cardNumberLabel);
    panel.add(cardNumberField);
    panel.add(resultArea);
    panel.add(blockButton);
    panel.add(unlockButton); unlockButton.setVisible(false);
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
    unlockButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String cardNumber = cardNumberField.getText();
        if (creditCards.containsKey(cardNumber)) {
          creditCards.put(cardNumber, false);
          resultArea.setText("Card Unlocked.");
        } else {
          resultArea.setText("Card not found.");
        }
      }
    });
    cardNumberField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String cardNumber = cardNumberField.getText();
        if(creditCards.containsKey(cardNumber)){
          if(creditCards.get(cardNumber)){
            blockButton.setVisible(false);
            unlockButton.setVisible(true);
          }else {
            blockButton.setVisible(true);
            unlockButton.setVisible(false);
          }
        }else{
          resultArea.setText("Card not found.");
        }
      }
    });

    add(panel);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new CreditCard());
  }
}