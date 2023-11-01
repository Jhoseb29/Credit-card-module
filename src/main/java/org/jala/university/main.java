package org.jala.university;

import org.jala.university.presentation.ButtonCreditCard;
import org.jala.university.presentation.ButtonSolitationForm;

import javax.swing.*;
import java.util.Date;

public class main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ButtonCreditCard buttonCreditCard = new ButtonCreditCard();  // Muestra la vista del botón
            buttonCreditCard.setVisible(true);

            ButtonSolitationForm buttonSolitationForm = new ButtonSolitationForm();  // Muestra la vista del botón
            buttonSolitationForm.setVisible(true);

        });
    }
}
