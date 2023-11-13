package org.jala.university.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfazView extends  JFrame {

    private JFrame frame;

    public InterfazView() {

        frame = new JFrame("Interfaz");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JButton btnStatus = new JButton("Credit Card");

        frame.add(btnStatus);

        frame.setVisible(true);
        btnStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreditCardView creditCardFormUI = new CreditCardView();
                creditCardFormUI.setVisible(true);
            }
        });
    }
}