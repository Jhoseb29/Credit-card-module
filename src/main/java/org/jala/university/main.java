package org.jala.university;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.presentation.InterfazView;
import org.jala.university.services.CreditCardImpl;

import javax.swing.*;
import java.util.UUID;

import static org.jala.university.utilities.Querys.validateCreditCard;

public class main {
    private static EntityManager entityManager;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
            InterfazView interfazUI = new InterfazView();
            System.out.println("NUMERO DE TARJETAS DE CREDITO CREADAS: " + validateCreditCard().getSingleResult());

        });
    }
}
