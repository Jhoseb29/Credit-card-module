package org.jala.university.presentation;

import org.jala.university.domain.CreditCardTableModule;
import org.jala.university.model.CreditCardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class InformationCreditCardView {
    private JFrame frame;
    private CreditCardTableModule creditCardModule;
    private UUID cardId;
    private CreditCardForm creditCardForm = CreditCardForm.builder().build();

    public InformationCreditCardView(CreditCardTableModule creditCardModule, UUID cardId) {
        this.creditCardModule = creditCardModule;
        this.cardId = cardId;

        frame = new JFrame("Tarjeta de Crédito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JButton btnEstado = new JButton("Ver Estado");
        JButton btnSaldo = new JButton("Ver Saldo");
        JButton btnLimiteSaldo = new JButton("Ver Límite de Saldo");
        JButton btnFechaVencimiento = new JButton("Ver Fecha de Vencimiento");

        frame.add(btnEstado);
        frame.add(btnSaldo);
        frame.add(btnLimiteSaldo);
        frame.add(btnFechaVencimiento);

        btnEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String estado = creditCardModule.getAccountStatus(cardId);
                JOptionPane.showMessageDialog(null, "Estado: " + estado);
            }
        });

        btnSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float saldo = creditCardModule.getCurrentLimit(cardId);
                JOptionPane.showMessageDialog(null, "Saldo: " + saldo);
            }
        });

        btnLimiteSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float limite = creditCardModule.getCredit_limit(cardId, creditCardForm.getIncome());
                JOptionPane.showMessageDialog(null, "Límite de Saldo: " + limite);
            }
        });

        btnFechaVencimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar la fecha de vencimiento

            }
        });

        frame.setVisible(true);
    }

}
