package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class SourceValue {
    private long valueLong;
    private String valueString;
    private double valueOrig;

    public SourceValue(double valueOrig) {
        this.valueOrig = valueOrig;
    }

    static void cast(SourceValue value1, SourceValue value2) {
        String decimalPart1 = Double.toString(value1.valueOrig).split("\\.")[1];
        String decimalPart2 = Double.toString(value2.valueOrig).split("\\.")[1];

        if ((!"0".equals(decimalPart1)) || (!"0".equals(decimalPart2))) {
            int koef = Math.max(decimalPart1.length(), decimalPart2.length());
            for (int i = 0; i < koef; i++) {
                value1.valueOrig *= 10;
                value2.valueOrig *= 10;
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

    long getFirstMinuend(SourceValue denominator) {
        return ((denominator.length() < length()) ? getFirstDigits(denominator.length()) : getLong());
    }

    long getNextMinuend(int numberOfNumeratorDigit, long residual) {
        return (numberOfNumeratorDigit >= length())
                ? residual * 10
                : residual * 10 + getDigit(numberOfNumeratorDigit);
    }

    public long getLong() {
        return valueLong;
    }

    public String getString() {
        return valueString;
    }
}
