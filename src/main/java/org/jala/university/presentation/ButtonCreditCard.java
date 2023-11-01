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
            // Aquí puedes agregar la lógica para lo que deseas hacer cuando se haga clic en el botón.
            // Por ejemplo, puedes abrir el formulario de tarjeta de crédito o realizar alguna otra acción.
        });
    }
}