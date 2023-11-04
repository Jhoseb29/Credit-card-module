package org.jala.university.presentation;

import ch.qos.logback.core.BasicStatusManager;
import ch.qos.logback.core.status.Status;

import javax.swing.*;
import java.awt.*;


public class InterfazView extends  JFrame{

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
    }
}
