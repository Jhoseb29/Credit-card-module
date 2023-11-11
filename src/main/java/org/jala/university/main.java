package org.jala.university;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.dao.RecordDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.presentation.CreditCardActionsView;
import org.jala.university.services.CreditCardImpl;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;
import org.jala.university.utilities.ControllerRecordCard;

import java.util.UUID;

public class main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CardModule");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            CreditCardDao creditCardDao = new CreditCardDao(entityManager);
            RecordDao recordDao = new RecordDao(UUID.class, RecordModel.class, entityManager);
            RecordImpl recordImpl = new RecordImpl(recordDao);
            CreditCardModule creditCardModule = new CreditCardImpl(creditCardDao);
            CreditCardModel creditCardModel = CreditCardModel.builder()
                    .credit_limit(3000)
                    .current_limit(2000)
                    .expiration_month(12)
                    .expiration_year(2027)
                    .approved_card(true)
                    .NIP(1234)
                    .status(1)
                    .build();

            creditCardModule.create(creditCardModel);
            ControllerRecordCard controllerRecordCard = new ControllerRecordCard(recordImpl, creditCardModel);
            CreditCardActionsView creditCardActionsView = new CreditCardActionsView(controllerRecordCard, recordImpl);
            creditCardActionsView.setVisible(true);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
