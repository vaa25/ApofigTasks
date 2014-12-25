package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Numerator extends SourceValue {
    private int digitCounter;
    public Numerator(double valueOrig) {
        super(valueOrig);
    }

    void setDigitCounter(SourceValue denominator) {
        digitCounter = getMinLength(this, denominator);
    }

    Minuend getFirstMinuend(SourceValue denominator) {
        return new Minuend((denominator.length() < length()) ? getFirstDigits(denominator.length()) : getValueLong());
    }

    Minuend getNextMinuend(Residual residual) {
        return (digitCounter >= length())
                ? new Minuend(residual.getValueLong() * TEN)
                : new Minuend(residual.getValueLong() * TEN + getDigit(digitCounter));
    }

    boolean isNeedDot() {
        return (digitCounter >= length());

    }

    boolean doesAllNumeratorDigitsWereInWork() {
        return (digitCounter > length());
    }

    void increaseDigitCounter() {
        digitCounter++;
    }

    private long getDigit(int index) {
        return Long.valueOf(valueString.substring(index, index + 1));
    }

    private long getFirstDigits(int to) {
        return Long.valueOf(valueString.substring(0, to));
    }
}
