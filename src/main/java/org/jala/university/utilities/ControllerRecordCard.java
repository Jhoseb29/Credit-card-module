package org.jala.university.utilities;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.services.RecordImpl;


import java.time.LocalDateTime;

public class ControllerRecordCard {
    private final CreditCardModel creditCardModel;
    private final RecordImpl recordImpl;
    public ControllerRecordCard(RecordImpl recordImpl, CreditCardModel creditCardModel) {
        this.recordImpl = recordImpl;
        this.creditCardModel = creditCardModel;
    }
    public String getTypeOfOperation(EnumOperations operation) {
       return operation.getOperationsName();
    }
    public void updatePin(int pin) {
        creditCardModel.setNIP(pin);
        logAction(EnumOperations.UPDATE_PIN.getOperationsName());
    }
    public void updateStatus(int status) {
        creditCardModel.setStatus(status);
        logAction(EnumOperations.UPDATE_STATUS.getOperationsName());
    }
    public int pay(int mount){
        int currentBalance = (int) creditCardModel.getCurrent_limit();
        currentBalance -= mount;
        creditCardModel.setCurrent_limit(currentBalance);
        logAction(EnumOperations.PAY.getOperationsName());
        return currentBalance;

    }
    private void logAction(String action){
        String logMessage = String.format("Action: %s, Date: %s", action, LocalDateTime.now());
        RecordModel logRecord = RecordModel.builder()
                .operation(action)
                .current_limit(creditCardModel.getCurrent_limit())
                .PIN(creditCardModel.getNIP())
                .status(creditCardModel.getStatus())
                .date(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .creditCard(creditCardModel)
                .build();
        recordImpl.create(logRecord);


    }

}
