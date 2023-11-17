package org.jala.university;


import jakarta.persistence.*;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.presentation.InterfazView;
import org.jala.university.services.CreditCardImpl;
import org.jala.university.services.CreditCardModule;
import org.jala.university.utilities.CreditCardUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;

import static org.jala.university.utilities.Querys.validateCreditCard;

public class main {
    private static EntityManager entityManager;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
            InterfazView interfazUI = new InterfazView(entityManagerFactory);

        });
    }
}
