package org.jala.university.utilities;

import javax.swing.*;

public class Dialog {
    public static void error(String reason) {
        JOptionPane.showMessageDialog(null, reason, "ERROR", JOptionPane.ERROR_MESSAGE);

    }
    public static void getInformation(String message){
        JOptionPane.showMessageDialog(null, message, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

    }
}
