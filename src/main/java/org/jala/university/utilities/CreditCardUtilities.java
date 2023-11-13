package org.jala.university.utilities;

import java.util.Random;

public class CreditCardUtilities {
    static Random random = new Random();
    public static String generateCreditCardBin(){
        StringBuilder cardBin = new StringBuilder("4");
        for(int i = 1; i <= 15; i++){
            int digit = random.nextInt(10);
            cardBin.append(digit);
        }
        return cardBin.toString();
    }

    public static int generateCreditCardCVV(){
        return random.nextInt(900) + 100;
    }

}
