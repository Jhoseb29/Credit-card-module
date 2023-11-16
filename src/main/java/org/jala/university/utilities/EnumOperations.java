package org.jala.university.utilities;

public enum EnumOperations {
    PAY("A payment was made."),
    UPDATE_PIN("I did update the pin"),
    UPDATE_STATUS("Card status changed"),
    WITHDRAW_CASH("Cash withdrawal");

    private final String operationsName;

    EnumOperations(String operationsName) {
        this.operationsName = operationsName;
    }

    public String getOperationsName() {
        return operationsName;
    }
}
