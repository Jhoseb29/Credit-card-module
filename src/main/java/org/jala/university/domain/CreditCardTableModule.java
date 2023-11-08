package org.jala.university.domain;

import org.jala.university.model.CreditCardTable;
import java.util.List;
import java.util.UUID;

public interface CreditCardTableModule {
    void create(CreditCardTable creditCard);
    CreditCardTable get(UUID id);
    List<CreditCardTable> getAll();
    CreditCardTable update(CreditCardTable creditCard);
    void delete(UUID id);
    float getCurrentLimit(UUID id);
    float getCredit_limit(UUID id, double income);
    String getAccountStatus(UUID id);
}
