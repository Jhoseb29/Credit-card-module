package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.dao.CreditCardTableDao;
import org.jala.university.utilities.InfoCreditCard;
import org.jala.university.services.CreditCardTableImpl;
import org.jala.university.services.CreditCardTableModule;
import org.jala.university.model.FormModel;
import org.jala.university.services.CreditCardModule;
import org.jala.university.utilities.Dialog;
import org.jala.university.utilities.Validator;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    String[] labels = {"Address", "Cellphone", "Income", "Birthdate (dd/mm/aaaa)", "Email"};
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
      dispose();
      String address = inputFields.get("Address").getText();
      String phoneNumber = inputFields.get("Cellphone").getText();
      String incomeText = inputFields.get("Income").getText();
      String birthdateText = inputFields.get("Birthdate (dd/mm/aaaa)").getText();
      String email = inputFields.get("Email").getText();

      // Verifica si algún campo está vacío
      if (address.isEmpty() || phoneNumber.isEmpty() || incomeText.isEmpty() || birthdateText.isEmpty() || email.isEmpty()) {
        Dialog.error("Por favor, complete todos los campos.");
        return;
      }

      //Validators
      double income;
      Date birthdate;
      try {
        income = Double.parseDouble(incomeText);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        birthdate = dateFormat.parse(birthdateText);
      } catch (ParseException | NumberFormatException ex) {
        Dialog.error("Por favor, ingrese datos válidos.");
        return;
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


      FormModel creditCardForm = FormModel.builder()
          .address(address)
          .phoneNumber(phoneNumber)
          .income(income)
          .birthdate(birthdate)
          .email(email)
          .aplicationDate(new Date())
          .build();
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      creditCardModule.create(creditCardForm);
      transaction.commit();
      //creditCardModule.create(creditCardForm);
      clearFormFields();
      //System.out.println(income);
      //creditCardForm.setIncome(income);
      //System.out.println("guardado en la tarjeta" + creditCardForm.getIncome());
      CreditCardTableModule creditCardTableModule = new CreditCardTableImpl(new CreditCardTableDao(entityManager));
      InfoCreditCard infoCreditCard = new InfoCreditCard(creditCardTableModule, entityManager, creditCardForm);
      UUID cardId = infoCreditCard.generateCreditCardData();
      SwingUtilities.invokeLater(() -> new InformationCreditCardView(creditCardTableModule, cardId));

    });
  }

  private void clearFormFields() {
    for (JTextField textField : inputFields.values()) {
      textField.setText("");
    }
  }
}
