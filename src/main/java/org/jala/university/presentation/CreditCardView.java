package org.jala.university.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.dao.FormDao;
import org.jala.university.dao.RecordDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.services.*;
import org.jala.university.controllers.ControllerCreditCard;
import org.jala.university.model.FormModel;
import org.jala.university.utilities.CreditCardUtilities;
import org.jala.university.utilities.Dialog;
import org.jala.university.utilities.Validator;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CreditCardView extends JFrame {
  private final JPanel topPanel;
  private final JPanel btnPanel;
  private  EntityManager entityManager;
  Map<String, JTextField> inputFields = new HashMap<>();

  public CreditCardView() {
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
    //SpringUtilities.makeCompactGrid(topPanel, numPairs, 2, 6, 6, 6, 6);
    JButton submitButton = new JButton("SEND REQUEST");
    btnPanel.add(submitButton);
    submitButton.addActionListener(event -> {
      String address = inputFields.get("Address").getText();
      String phoneNumber = inputFields.get("Cellphone").getText();
      String incomeText = inputFields.get("Income").getText();
      String birthdateText = inputFields.get("Birthdate (dd/mm/aaaa)").getText();
      String email = inputFields.get("Email").getText();
      Date birthdate = null;

      // Validación de campos vacíos
      if (address.isEmpty() || phoneNumber.isEmpty() || incomeText.isEmpty() || birthdateText.isEmpty() || email.isEmpty()) {
        Dialog.error("Por favor, complete todos los campos.");
        return;
      }

      double income;
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date applicationDate = new Date();

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
        income = Double.parseDouble(incomeText);
        if (!Validator.isValidIncome(income)) {
          Dialog.error("Los ingresos no pueden ser negativos.");
          return;
        }
      } catch (NumberFormatException ex) {
        Dialog.error("Ingrese un valor válido para ingresos.");
        return;
      }
      Dialog.getInformation("Formulario aceptado. ¡Gracias por enviar su solicitud!");


      FormModel formModel = FormModel.builder()
          .address(address)
          .phoneNumber(phoneNumber)
          .income(income)
          .birthdate(birthdate)
          .email(email)
          .aplicationDate(applicationDate)
          .build();

      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
      entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        FormModule formModule = new FormImpl(new FormDao(entityManager));
        formModule.create(formModel);
        double creditLimit = income * 1.5;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 4);
        int expirationMonth = calendar.get(Calendar.MONTH) + 1;
        int expirationYear = calendar.get(Calendar.YEAR);
        CreditCardModule creditCardTableModule = new CreditCardImpl(new CreditCardDao(entityManager));
        CreditCardModel creditCardModel = CreditCardModel.builder()
                .formModel(formModel)
                .approved_card(true)
                .credit_limit(creditLimit)
                .current_limit(creditLimit)
                .NIP(generateRandomPIN())
                .card(CreditCardUtilities.generateCreditCardBin())
                .cvv(CreditCardUtilities.generateCreditCardCVV())
                .status(1)
                .expiration_year(expirationYear)
                .expiration_month(expirationMonth)
                .records(new ArrayList<>())
                .build();
        creditCardTableModule.create(creditCardModel);
        transaction.commit();
        clearFormFields();
        RecordImpl record = new RecordImpl(new RecordDao(UUID.class, RecordModel.class, entityManager));
        ControllerCreditCard controllerRecordCard = new ControllerCreditCard(creditCardModel, entityManager, creditCardTableModule, record );
        SwingUtilities.invokeLater(() -> new InformationCreditCardView(creditCardModel, controllerRecordCard, record));

      }
      catch (Exception e) {
        if (transaction.isActive()) {
          transaction.rollback();
        }
        e.printStackTrace();

      }


    });
  }
  private void clearFormFields() {
    for (JTextField textField : inputFields.values()) {
      textField.setText("");
    }
  }
  private int generateRandomPIN() {
    Random random = new Random();
    int pin = 1000 + random.nextInt(9000);
    return pin;
  }
}
