package org.jala.university.presentation;

import org.jala.university.controllers.ControllerCreditCard;

import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class ProgramarPagoView extends JDialog {
    private final ControllerCreditCard controllerCreditCard;

    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> yearComboBox;
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

        dayComboBox = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
        monthComboBox = new JComboBox<>(IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new));
        yearComboBox = new JComboBox<>(IntStream.rangeClosed(LocalDate.now().getYear(), LocalDate.now().getYear() + 10).boxed().toArray(Integer[]::new));
        montoField = new JTextField();

        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Día de Pago:"));
        panel.add(dayComboBox);
        panel.add(new JLabel("Mes de Pago:"));
        panel.add(monthComboBox);
        panel.add(new JLabel("Año de Pago:"));
        panel.add(yearComboBox);
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
            int day = (int) dayComboBox.getSelectedItem();
            int month = (int) monthComboBox.getSelectedItem();
            int year = (int) yearComboBox.getSelectedItem();

            LocalDate fechaPago = LocalDate.of(year, month, day);
            int montoPago = Integer.parseInt(montoField.getText());

            controllerCreditCard.programarPagoAutomatico(fechaPago, montoPago);
            dispose();
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una fecha y un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
