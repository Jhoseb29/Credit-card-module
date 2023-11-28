package org.jala.university.utilities;

import java.util.Calendar;
import java.util.Random;

public class CreditCardUtilities {
    static Random random = new Random();

    public static String generateCreditCardBin() {
        StringBuilder cardBin = new StringBuilder("4");
        for (int i = 1; i <= 3; i++) {
            int digit = random.nextInt(10);
            cardBin.append(digit);
        }
        cardBin.append(" ");
        for (int i = 1; i <= 12; i++) {
            int digit = random.nextInt(10);
            cardBin.append(digit);
            if (i % 4 == 0) {
                cardBin.append(" ");
            }
        }
        return cardBin.toString();
    }

    public static int generateCreditCardCVV() {
        return random.nextInt(900) + 100;
    }

    public static int calculateCreditLimit(double income) {
        return (int) (income * 1.5);

    }

    public static int calculateExpirationMonth() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return currentMonth;

    }

    public static int calculateExpirationYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        int expirationYear = currentYear + 4;
        return expirationYear;
    }

    public static int generateRandomPIN() {
        Random random = new Random();
        int pin = 1000 + random.nextInt(9000);
        return pin;
    }

}
