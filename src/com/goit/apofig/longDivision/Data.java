package com.goit.apofig.longDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Vlasov
 */
class Data {
    private final int ABSENT = -1;
    private List<Long> residuals = new ArrayList<>();
    private List<Long> minuends = new ArrayList<>();
    private List<Long> subtrahends = new ArrayList<>();
    private List<Long> answerParts = new ArrayList<>();
    private int dotIndex;
    private int startPeriod;
    private SourceValue numerator;
    private SourceValue denominator;

    Data(double numerator, double denominator) {
        this.numerator = new SourceValue(numerator);
        this.denominator = new SourceValue(denominator);
        SourceValue.cast(this.numerator, this.denominator);
    }

    public SourceValue getNumerator() {
        return numerator;
    }

    public SourceValue getDenominator() {
        return denominator;
    }

    List<Long> getMinuends() {
        return minuends;
    }

    List<Long> getResiduals() {
        return residuals;
    }

    List<Long> getSubtrahends() {
        return subtrahends;
    }

    List<Long> getAnswerParts() {
        return answerParts;
    }

    int getDotIndex() {
        return dotIndex;
    }

    int getStartPeriodIndex() {
        return startPeriod;
    }

    void calculate() {
        dotIndex = ABSENT;
        startPeriod = ABSENT;
        calculate1();
        correctFirstZero();
    }

    private void correctFirstZero() {
        if (answerParts.get(0) == 0 && (dotIndex > 1)) {
            answerParts.remove(0);
            residuals.remove(0);
            minuends.remove(0);
            subtrahends.remove(0);
            dotIndex--;
            if (hasIrrationalAnswer()) {
                startPeriod--;
            }
        }
    }

    boolean hasIrrationalAnswer() {
        return startPeriod != ABSENT;
    }

    private void calculate1() {

        int numberOfNumeratorDigit = SourceValue.getMinLength(numerator, denominator);
        long minuend = numerator.getFirstMinuend(denominator);
        do {
            long answerPart = minuend / denominator.getLong();
            long subtrahend = answerPart * denominator.getLong();
            long residual = minuend - subtrahend;
            if (isIrrationalSymptomDetected(residual)) {
                setPeriod(residual);
            }
            minuend = getNextMinuend(numberOfNumeratorDigit, residual);
            addValuesToLists(minuend, answerPart, subtrahend, residual);
            if (isNeedDot(numberOfNumeratorDigit)) {
                setDot();
            }
            numberOfNumeratorDigit++;
        } while (!hasIrrationalAnswer() && !hasRationalAnswer(numberOfNumeratorDigit));
    }

    private boolean hasRationalAnswer(int numberOfNumeratorDigit) {
        return (isRationalSymptomDetected() && doesAllNumeratorDigitsWereInWork(numberOfNumeratorDigit));
    }

    private long getNextMinuend(int numberOfNumeratorDigit, long residual) {
        if (hasIrrationalAnswer()) {
            return residual;
        } else {
            return numerator.getNextMinuend(numberOfNumeratorDigit, residual);
        }
    }

    private boolean doesAllNumeratorDigitsWereInWork(int numberOfNumeratorDigit) {
        return (numberOfNumeratorDigit > numerator.length());
    }

    boolean isRationalSymptomDetected() {
        return (residuals.get(residuals.size() - 1) == 0);
    }

    private void addValuesToLists(long minuend, long answerPart, long subtrahend, long residual) {
        subtrahends.add(subtrahend);
        minuends.add(minuend);
        answerParts.add(answerPart);
        residuals.add(residual);
    }

    private void setDot() {
        dotIndex = answerParts.size();
    }

    private void setPeriod(long residual) {
        startPeriod = residuals.lastIndexOf(residual) + 1;

    }

    private boolean isNeedDot(int numberOfNumeratorDigit) {
        return (!hasDot()) && (numberOfNumeratorDigit >= numerator.length());
    }

    private boolean isIrrationalSymptomDetected(long residual) {
        return (hasDot() && (residuals.lastIndexOf(residual) >= dotIndex - 1));
    }

    private boolean hasDot() {
        return (dotIndex != ABSENT);
    }

}
