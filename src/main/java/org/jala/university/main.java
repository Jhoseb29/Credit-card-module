package org.jala.university;


import jakarta.persistence.*;
import org.jala.university.presentation.InterfazView;
import javax.swing.*;


public class main {
    private static EntityManager entityManager;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
            InterfazView interfazUI = new InterfazView(entityManagerFactory);

        });
    }
}
