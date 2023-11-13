package org.jala.university;


import org.jala.university.presentation.InterfazView;

import javax.swing.*;

public class main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            InterfazView interfazUI = new InterfazView();

        });
    }
}
