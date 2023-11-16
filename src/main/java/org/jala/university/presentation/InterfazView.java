package org.jala.university.presentation;
import jakarta.persistence.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.jala.university.utilities.Querys.validateCreditCard;


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

                System.out.println("DENTRO DEL BOTON: " + validateCreditCard().getSingleResult());

                if (((int) validateCreditCard().getResultList().get(0)) == 0 ){
                    CreditCardView creditCardFormUI = new CreditCardView();
                    creditCardFormUI.setVisible(true);
                    System.out.println("ENTRO EN EL IF");
                }else{ }

            }
        });
    }
}