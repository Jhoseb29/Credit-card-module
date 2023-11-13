package org.jala.university.Services;

import org.jala.university.model.CreditCardModel;
import java.util.List;
import java.util.UUID;

public interface CreditCardModule {
    void create(CreditCardModel creditCard);
    CreditCardModel get(UUID id);
    List<CreditCardModel> getAll();
    CreditCardModel update(CreditCardModel creditCard);
    void delete(UUID id);
    float getCurrentLimit(UUID id);
    float getCredit_limit(UUID id, double income);
    String getAccountStatus(UUID id);
    String getExpirationDate(UUID id);

    boolean cancelCreditCard(UUID id);

    void cancelCreditCardByNumber(String cardNumber) throws Exception;
}
