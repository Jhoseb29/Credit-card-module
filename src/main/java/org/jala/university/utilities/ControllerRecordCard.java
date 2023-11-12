package org.jala.university.utilities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.dao.RecordDao;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


public class ControllerRecordCard {
    private final CreditCardModel creditCardModel;
    private final EntityManager entityManager;
    private final   CreditCardModule creditCardModule;
    private final RecordImpl record;

    public ControllerRecordCard(CreditCardModel creditCardModel, EntityManager entityManager, CreditCardModule creditCardModule, RecordImpl record ) {
        this.creditCardModel = creditCardModel;
        this.entityManager = entityManager;
        this.creditCardModule = creditCardModule;
        this.record = record;
    }
    public String getTypeOfOperation(EnumOperations operation) {
       return operation.getOperationsName();

    }
    public void updatePin(int pin) {
        creditCardModel.setNIP(pin);
        logAction(EnumOperations.UPDATE_PIN.getOperationsName());
        creditCardModule.update(creditCardModel);
        commitTransaction();
    }
    public void updateStatus(int status) {
        creditCardModel.setStatus(status);
        logAction(EnumOperations.UPDATE_STATUS.getOperationsName());
        creditCardModule.update(creditCardModel);
        commitTransaction();
    }
    public int pay(int mount){
        int currentBalance = (int) creditCardModel.getCurrent_limit();
        currentBalance -= mount;
        creditCardModel.setCurrent_limit(currentBalance);
        logAction(EnumOperations.PAY.getOperationsName());
        creditCardModule.update(creditCardModel);
        commitTransaction();
        return currentBalance;

    }

    private void logAction(String action){
        String logMessage = String.format("Action: %s, Date: %s", action, LocalDateTime.now());
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        RecordModel logRecord = RecordModel.builder()
                .operation(action)
                .current_limit(creditCardModel.getCurrent_limit())
                .PIN(creditCardModel.getNIP())
                .status(creditCardModel.getStatus())
                .date(new Date())
                .creditCard(creditCardModel)
                .build();
        record.create(logRecord);
        commitTransaction();
    }
    private void commitTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (transaction.isActive()) {
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
                entityManager.close();
            }
            e.printStackTrace();
        }
    }

}
