package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jala.university.model.CreditCardForm;
import org.jala.university.domain.CreditCardModule;
import org.jala.university.validations.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreditCardView extends JFrame {
    /*private final CreditCardModule creditCardModule;
    private CreditCardModule creditCardModule1;
    private JPanel topPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;


    public CreditCardView(CreditCardModule creditCardModule) {
        this.creditCardModule = creditCardModule;
        setTitle("credit Card Form");
        setSize(500, 500);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
    }*/
    private final CreditCardModule creditCardModule;

    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField incomeField;
    private JTextField birthdateField;
    private JTextField emailField;

    public CreditCardView(CreditCardModule creditCardModule) {
        this.creditCardModule = creditCardModule;

        setTitle("Credit Card Form");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        addressField = new JTextField();
        phoneNumberField = new JTextField();
        incomeField = new JTextField();
        birthdateField = new JTextField();
        emailField = new JTextField();
        JButton submitButton = new JButton("SEND REQUEST");

        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Cellphone:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Income:"));
        panel.add(incomeField);
        panel.add(new JLabel("Birthdate (dd/mm/aaaa):"));
        panel.add(birthdateField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel(""));

        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de entrada
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String incomeText = incomeField.getText();
                String birthdateText = birthdateField.getText();
                String email = emailField.getText();

                // Realizar validaciones
                if (!Validator.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(CreditCardView.this, "Correo electrónico no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double income;
                try {
                    income = Double.parseDouble(incomeText);
                    if (!Validator.isValidIncome(income)) {
                        JOptionPane.showMessageDialog(CreditCardView.this, "Los ingresos no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreditCardView.this, "Ingrese un valor válido para ingresos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Validator.isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(CreditCardView.this, "Número de teléfono no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Validator.isValidBirthdate(birthdateText)) {
                    JOptionPane.showMessageDialog(CreditCardView.this, "Fecha de nacimiento no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date birthdate;
                try {
                    birthdate = dateFormat.parse(birthdateText);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(CreditCardView.this, "Error al procesar la fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                CreditCardForm newCreditCard = new CreditCardForm();
                newCreditCard.setAddress(address);
                newCreditCard.setPhoneNumber(phoneNumber);
                newCreditCard.setIncome(income);
                newCreditCard.setBirthdate(birthdate);
                newCreditCard.setEmail(email);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("CardModule");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(newCreditCard);
                em.getTransaction().commit();
                em.close();
                emf.close();

                System.out.println("Éxito: Los datos se han guardado en la base de datos.");
                clearFormFields();
            }
        });
    }

    private void clearFormFields() {
        addressField.setText("");
        phoneNumberField.setText("");
        incomeField.setText("");
        birthdateField.setText("");
        emailField.setText("");
    }



}
