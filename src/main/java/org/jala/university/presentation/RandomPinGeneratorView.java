package org.jala.university.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomPinGeneratorView extends JFrame {

  private JLabel pinLabel;
  private JButton generateButton;

  public RandomPinGeneratorView() {
    setTitle("PIN");
    setSize(300, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    pinLabel = new JLabel("", JLabel.CENTER);
    generateButton = new JButton("Generar PIN");

    generateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String pin = generateRandomPIN();
        pinLabel.setText("PIN Aleatorio: " + pin);
      }
    });

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(pinLabel, BorderLayout.CENTER);
    panel.add(generateButton, BorderLayout.SOUTH);

    add(panel);
  }

  private String generateRandomPIN() {
    Random random = new Random();
    int pin = 1000 + random.nextInt(9000);
    return String.valueOf(pin);
  }
}
