package com.bankkata.exercise.BankKata.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
    /**
     * Static Method to round double values based on the quantity of places
     * @param value
     * @param places
     * @return
     */
    public static double roundDouble(double value, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
