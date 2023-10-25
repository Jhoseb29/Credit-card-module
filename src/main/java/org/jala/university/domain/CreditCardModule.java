package org.jala.university.domain;

import org.jala.university.model.CreditCardForm;

import java.util.List;
import java.util.UUID;

public interface CreditCardModule {
    void create(CreditCardForm creditCard);
    CreditCardForm get(UUID id);
    List<CreditCardForm> getAll();
    CreditCardForm update(CreditCardForm creditCard);
    void delete(UUID id);
}
