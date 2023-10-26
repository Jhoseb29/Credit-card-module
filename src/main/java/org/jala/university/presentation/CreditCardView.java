package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.model.CreditCardForm;
import org.jala.university.domain.CreditCardModule;
import org.jala.university.validations.Dialog;
import org.jala.university.validations.Validator;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CreditCardView extends JFrame {

  private final CreditCardModule creditCardModule;
  private final JPanel topPanel;
  private final JPanel btnPanel;
  private final EntityManager entityManager;
  Map<String, JTextField> inputFields = new HashMap<>();

  public CreditCardView(CreditCardModule creditCardModule, EntityManager entityManager) {
    this.creditCardModule = creditCardModule;
    this.entityManager = entityManager;
    setTitle("Credit Card Form");
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    String[] labels = {"Address", "Cellphone", "Income", "Birthdate (dd/mm/aaaa)", "Email",
        "Application Date (dd/mm/aaaa)"};
    int numPairs = labels.length;
    topPanel = new JPanel(new SpringLayout());
    btnPanel = new JPanel();
    add(topPanel, BorderLayout.CENTER);
    add(btnPanel, BorderLayout.SOUTH);

    for (String labelName : labels) {
      JLabel label = new JLabel(labelName + " : ", JLabel.TRAILING);
      topPanel.add(label);
      JTextField jTextField = new JTextField(15);
      label.setLabelFor(jTextField);
      inputFields.put(labelName, jTextField);
      topPanel.add(jTextField);
    }
    SpringUtilities.makeCompactGrid(topPanel, numPairs, 2, 6, 6, 6, 6);
    JButton submitButton = new JButton("SEND REQUEST");
    btnPanel.add(submitButton);
    submitButton.addActionListener(event -> {
      String address = inputFields.get("Address").getText();
      String phoneNumber = inputFields.get("Cellphone").getText();
      double income = Double.parseDouble(inputFields.get("Income").getText());
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date applicationDate = new Date();
      String email = inputFields.get("Email").getText();
      Date birthdate = null;

      //Validators
      try {
        birthdate = dateFormat.parse(inputFields.get("Birthdate (dd/mm/aaaa)").getText());
      } catch (ParseException ex) {
        Dialog.error("Error al procesar la fecha de nacimiento.");

      }

      if (!Validator.isValidBirthdate(inputFields.get("Birthdate (dd/mm/aaaa)").getText())) {
        Dialog.error("La persona debe tener al menos 18 años.");
        return;
      }

      if (!Validator.isValidEmail(email)) {
        Dialog.error("Correo electrónico no válido.");
        return;
      }
      if (!Validator.isValidPhoneNumber(phoneNumber)) {
        Dialog.error("Número de teléfono no válido.");
        return;
      }
      try {
        if (!Validator.isValidIncome(income)) {
          Dialog.error("Los ingresos no pueden ser negativos.");
          return;
        }
      } catch (NumberFormatException ex) {
        Dialog.error("Ingrese un valor válido para ingresos.");
        return;
      }
      Dialog.getInformation("Formulario aceptado. ¡Gracias por enviar su solicitud!");

      CreditCardForm creditCardForm = CreditCardForm.builder()
          .address(address)
          .phoneNumber(phoneNumber)
          .income(income)
          .birthdate(birthdate)
          .email(email)
          .aplicationDate(applicationDate)
          .build();
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      creditCardModule.create(creditCardForm);
      transaction.commit();
      creditCardModule.create(creditCardForm);
      clearFormFields();
    });
  }

  private void clearFormFields() {
    for (JTextField textField : inputFields.values()) {
      textField.setText("");
    }
  }
}
