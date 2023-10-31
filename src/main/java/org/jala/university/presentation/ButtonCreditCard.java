package org.jala.university.presentation;
import javax.swing.*;
import java.awt.*;

public class ButtonCreditCard extends JFrame {

    public ButtonCreditCard() {
        setTitle("Button Credit Card");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JButton creditCardButton = new JButton("Tarjeta de Crédito");
        panel.add(creditCardButton);

        creditCardButton.addActionListener(e -> {
            CardView cardView = new CardView();  // Muestra la vista de la tarjeta
            cardView.setVisible(true);
            setVisible(false);
        });
    }
}