package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Numerator extends SourceValue {
    public Numerator(double valueOrig) {
        super(valueOrig);
    }

    long getDigit(int index) {
        return Long.valueOf(valueString.substring(index, index + 1));
    }

    long getFirstDigits(int to) {
        return Long.valueOf(valueString.substring(0, to));
    }

//    }

    Minuend getFirstMinuend(SourceValue denominator) {
        return new Minuend((denominator.length() < length()) ? getFirstDigits(denominator.length()) : getValueLong());
    }

//    }

//    }

    public void setDigitCounter(SourceValue denominator) {
        digitCounter = getMinLength(this, denominator);
    }

    public Minuend getNextMinuend(Residual residual) {
        return (digitCounter >= length())
                ? new Minuend(residual.getValueLong() * TEN)
                : new Minuend(residual.getValueLong() * TEN + getDigit(digitCounter));
    }

    public boolean isNeedDot() {
        return (digitCounter >= length());

    }

    public boolean doesAllNumeratorDigitsWereInWork() {
        return (digitCounter > length());
    }

    public void increaseDigitCounter() {
        digitCounter++;
    }
}
