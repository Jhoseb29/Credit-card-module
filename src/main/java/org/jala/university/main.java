package org.jala.university;

import org.jala.university.presentation.ButtonCreditCard;

import javax.swing.*;

public class main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ButtonCreditCard buttonCreditCard = new ButtonCreditCard();  // Muestra la vista del botón
            buttonCreditCard.setVisible(true);

        });
    }
}
