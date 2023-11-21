package org.jala.university.presentation;


import org.jala.university.controllers.ControllerCreditCard;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
//import com.toedter.calendar.JDateChooser;


public class ProgramarPagoView extends JDialog {
    private final ControllerCreditCard controllerCreditCard;

    //private JDateChooser dateChooser;
    private JTextField montoField;

    public ProgramarPagoView(ControllerCreditCard controllerCreditCard) {
        this.controllerCreditCard = controllerCreditCard;
        initialize();
    }

    private void initialize() {
        setTitle("Programar Pago Automático");
        setSize(300, 150);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //dateChooser = new JDateChooser();
        montoField = new JTextField();

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Fecha de Pago:"));
        //panel.add(dateChooser);
        panel.add(new JLabel("Monto:"));
        panel.add(montoField);
        panel.add(confirmButton);
        panel.add(cancelButton);

        add(panel);

        confirmButton.addActionListener(e -> confirmarProgramacion());
        cancelButton.addActionListener(e -> dispose());
    }

    private void confirmarProgramacion() {
        try {
            LocalDate fechaPago = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int montoPago = Integer.parseInt(montoField.getText());

            controllerCreditCard.programarPagoAutomatico(fechaPago, montoPago);
            JOptionPane.showMessageDialog(this, "Pago programado correctamente.");
            dispose();
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una fecha y un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
