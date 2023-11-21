package org.jala.university.controllers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jala.university.model.CreditCardModel;
import org.jala.university.model.RecordModel;
import org.jala.university.services.CreditCardModule;
import org.jala.university.services.RecordImpl;
import org.jala.university.utilities.Dialog;
import org.jala.university.utilities.EnumOperations;
import org.jala.university.utilities.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class ControllerCreditCard {
    private final CreditCardModel creditCardModel;
    private final EntityManager entityManager;
    private final   CreditCardModule creditCardModule;
    private final RecordImpl record;

    public ControllerCreditCard(CreditCardModel creditCardModel, EntityManager entityManager, CreditCardModule creditCardModule, RecordImpl record ) {
        this.creditCardModel = creditCardModel;
        this.entityManager = entityManager;
        this.creditCardModule = creditCardModule;
        this.record = record;
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
        logAction(EnumOperations.PAY.getOperationsName() + currentBalance);
        creditCardModule.update(creditCardModel);
        commitTransaction();
        return currentBalance;

    }

    public int withdrawCash(int mount) {
        int currentBalance = (int) creditCardModel.getCurrent_limit();
        int statusCard = creditCardModel.getStatus();
        if (!Validator.isValidWithdrawal(mount, currentBalance, statusCard)) {
            System.out.println("Invalid withdrawal amount. Please check your balance or review the status of the card");
            return currentBalance;
        }
        int desc = (int) (mount * 0.05);
        if (desc >= currentBalance) {
            System.out.println("Insufficient funds. Please check your balance.");
            return currentBalance;
        }
        currentBalance -= mount + desc;
        creditCardModel.setCurrent_limit(currentBalance);
        logAction(EnumOperations.WITHDRAW_CASH.getOperationsName() + mount);
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

    public void programarPagoAutomatico(LocalDate fechaPago, int montoPago) {
        double saldoActual = creditCardModel.getCurrent_limit();

        // Verificar si hay suficiente saldo para el pago
        if (saldoActual >= montoPago) {
            // Deducción del saldo de la tarjeta
            saldoActual -= montoPago;

            // Verificar si el saldo después del pago sigue siendo menor o igual al límite
            if (saldoActual <= creditCardModel.getCurrent_limit()) {
                // Crear un registro en el historial
                RecordModel recordModel = RecordModel.builder()
                        .date(java.sql.Date.valueOf(fechaPago))
                        .creditCard(creditCardModel)
                        .build();
                record.create(recordModel);
                logAction(EnumOperations.PAY.getOperationsName() + saldoActual);

                // Actualizar el saldo de la tarjeta
                creditCardModel.setCurrent_limit(saldoActual);
                creditCardModule.update(creditCardModel);
                commitTransaction();

                Dialog.getInformation("Pago programado para el " + fechaPago + " por un monto de " + montoPago);
            } else {
                Dialog.error("El pago automático excede el límite de crédito. Saldo insuficiente.");
            }
        } else {
            Dialog.error("Saldo insuficiente para programar el pago automático.");
        }
    }



}
