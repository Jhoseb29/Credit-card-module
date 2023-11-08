package org.jala.university.dates;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.domain.CreditCardTableModule;
import org.jala.university.model.CreditCardForm;
import org.jala.university.model.CreditCardTable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class InfoCreditCard {
    private  final CreditCardTableModule creditCardTableModule;
    private final EntityManager entityManager;

    public InfoCreditCard(CreditCardTableModule creditCardTableModule,  EntityManager entityManager1) {
        this.creditCardTableModule = creditCardTableModule;
        this.entityManager = entityManager1;

    }

    public UUID generateCreditCardData() {
        CreditCardForm creditCardForm = CreditCardForm.builder().build();
        double income = creditCardForm.getIncome();
        float creditLimit = (float) (income * 1.5f);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 4);
        int expirationMonth = calendar.get(Calendar.MONTH) + 1;
        int expirationYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        String expirationDate = dateFormat.format(calendar.getTime());
        boolean approved = income > 3000 ;

        CreditCardTable creditCardTable = CreditCardTable.builder()
                .current_limit(0.0f)
                .creditCardForm(creditCardForm)
                .credit_limit(creditLimit)
                .expiration_month(expirationMonth)
                .expiration_year(expirationYear)
                .approved_card(approved)
                .build();

        creditCardTableModule.create(creditCardTable);
        return creditCardTable.getId();
    }


}
