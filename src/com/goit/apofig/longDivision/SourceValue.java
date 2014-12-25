package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class SourceValue {
    private static final int TEN = 10;
    private long valueLong;
    private String valueString;
    private double valueOrig;
    private int digitCounter;
    public SourceValue(double valueOrig) {
        this.valueOrig = valueOrig;
    }


    static void cast(SourceValue value1, SourceValue value2) {
        String decimalPart1 = Double.toString(value1.valueOrig).split("\\.")[1];
        String decimalPart2 = Double.toString(value2.valueOrig).split("\\.")[1];

        if ((!"0".equals(decimalPart1)) || (!"0".equals(decimalPart2))) {
            int koef = Math.max(decimalPart1.length(), decimalPart2.length());
            for (int i = 0; i < koef; i++) {
                value1.valueOrig *= TEN;
                value2.valueOrig *= TEN;
            }
        }
        value1.cast();
        value2.cast();
    }

    static int getMinLength(SourceValue value1, SourceValue value2) {
        return Math.min(value1.length(), value2.length());
    }

    private void cast() {
        castToLong();
        castToString();
    }

    private void castToString() {
        valueString = String.valueOf(valueLong);
    }

    private void castToLong() {
        valueLong = ((Double) valueOrig).longValue();
    }

    long getDigit(int index) {
        return Long.valueOf(valueString.substring(index, index + 1));
    }

    int length() {
        return valueString.length();
    }

    long getFirstDigits(int to) {
        return Long.valueOf(valueString.substring(0, to));
    }
//
//    long getFirstMinuend(SourceValue denominator) {
//        return ((denominator.length() < length()) ? getFirstDigits(denominator.length()) : getValueLong());
//    }

    Minuend getFirstMinuend(SourceValue denominator) {
        return new Minuend((denominator.length() < length()) ? getFirstDigits(denominator.length()) : getValueLong());
    }

//    long getNextMinuend(int numberOfNumeratorDigit, long residual) {
//        return (numberOfNumeratorDigit >= length())
//                ? residual * TEN
//                : residual * TEN + getDigit(numberOfNumeratorDigit);
//    }

    Minuend getNextMinuend(int numberOfNumeratorDigit, Residual residual) {
        return (numberOfNumeratorDigit >= length())
                ? new Minuend(residual.getValueLong() * TEN)
                : new Minuend(residual.getValueLong() * TEN + getDigit(numberOfNumeratorDigit));
    }

    public long getValueLong() {
        return valueLong;
    }

    public String getString() {
        return valueString;
    }
}
