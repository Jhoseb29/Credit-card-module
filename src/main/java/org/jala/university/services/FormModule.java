package org.jala.university.services;

import org.jala.university.model.FormModel;

import java.util.List;
import java.util.UUID;

public interface FormModule {
    void create(FormModel creditCard);
    FormModel get(UUID id);
    List<FormModel> getAll();
    FormModel update(FormModel creditCard);
    void delete(UUID id);
    int checkCard() throws Exception;
}
