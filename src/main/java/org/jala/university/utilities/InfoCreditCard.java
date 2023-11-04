package org.jala.university.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.services.CreditCardTableModule;
import org.jala.university.model.FormModel;
import org.jala.university.model.CreditCardModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class InfoCreditCard {
    private  final CreditCardTableModule creditCardTableModule;
    private final EntityManager entityManager;
    private final FormModel creditCardForm;

    public InfoCreditCard(CreditCardTableModule creditCardTableModule,  EntityManager entityManager1, FormModel creditCardForm) {
        this.creditCardTableModule = creditCardTableModule;
        this.entityManager = entityManager1;
        this.creditCardForm = creditCardForm;

    }

    public UUID generateCreditCardData() {
        FormModel creditCardForm = FormModel.builder().build();
        double income = creditCardForm.getIncome();
        System.out.println("ID FORM" + creditCardForm.getId());
        System.out.println("INCOME " + creditCardForm.getIncome());
        double creditLimit = income * 1.5;
        System.out.println("LIMIT: " + creditLimit);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 4);
        int expirationMonth = calendar.get(Calendar.MONTH) + 1;
        int expirationYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        String expirationDate = dateFormat.format(calendar.getTime());
        boolean approved = income > 3000 ;
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CreditCardModel creditCardTable = CreditCardModel.builder()
                //.creditCardForm(creditCardForm)
                .credit_limit(creditLimit)
                .current_limit(creditLimit)
                .expiration_month(expirationMonth)
                .expiration_year(expirationYear)
                .approved_card(approved)
                .NIP(1234)
                .status(1)
                .build();
        creditCardTableModule.create(creditCardTable);
        transaction.commit();
        return creditCardTable.getId();
    }


}
