package org.jala.university.presentation;

import org.jala.university.domain.CreditCardModule;

import javax.swing.*;
import java.awt.*;

public class CreditCardView extends JFrame {
    private final CreditCardModule creditCardModule;
    private JPanel topPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;

    public CreditCardView(CreditCardModule creditCardModule) {
        this.creditCardModule = creditCardModule;

        setTitle("Account Module");
        setSize(500, 500);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
    }
}
