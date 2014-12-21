package com.goit.apofig.longDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Vlasov
 */
class Data {
    private List<Long> residuals = new ArrayList<>();
    private List<Long> minuends = new ArrayList<>();
    private List<Long> subtrahends = new ArrayList<>();
    private List<Long> answerParts = new ArrayList<>();
    private List<Integer> offsets = new ArrayList<>();
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

    List<Long> getSubtrahends() {
        return subtrahends;
    }

    List<Long> getAnswerParts() {
        return answerParts;
    }

    List<Integer> getOffsets() {
        return offsets;
    }

    int getDotIndex() {
        return dotIndex;
    }

    int getStartPeriod() {
        return startPeriod;
    }

    void calculate() {
        dotIndex = -1;
        startPeriod = -1;
        calculate1();
        correctSomePeriodic();
    }

    private void calculate1() {
        int offset = 0;
        long residual;
        int numberOfNumeratorDigit = SourceValue.getMinLength(numerator, denominator);
        long minuend = numerator.getFirstMinuend(denominator);
        do {
            long answerPart = minuend / denominator.getLong();
            long subtrahend = answerPart * denominator.getLong();
            residual = minuend - subtrahend;
            offset += getLengthOf(minuend) - getLengthOf(residual);
            addValuesToLists(offset, minuend, answerPart, subtrahend);
            minuend = numerator.getNextMinuend(numberOfNumeratorDigit, residual);
            if (isFoundIrrationalAnswer(residual)) {
                placePeriod(residual, minuend);
                break;
            }
            residuals.add(residual);
            placeDotIfNeed(numberOfNumeratorDigit);
            numberOfNumeratorDigit++;
        } while (!isFoundRationalAnswer(residual) || !doesAllNumeratorDigitsWereInWork(numberOfNumeratorDigit));
    }

    private boolean doesAllNumeratorDigitsWereInWork(int numberOfNumeratorDigit) {
        return numberOfNumeratorDigit > numerator.length();
    }

    private boolean isFoundRationalAnswer(long residual) {
        return residual == 0;
    }

    private void addValuesToLists(int offset, long minuend, long answerPart, long subtrahend) {
        subtrahends.add(subtrahend);
        minuends.add(minuend);
        answerParts.add(answerPart);
        offsets.add(offset);
    }

    private void placeDotIfNeed(int numberOfNumeratorDigit) {
        if (isNeedDot(numberOfNumeratorDigit)) {
            dotIndex = answerParts.size();
        }
    }

    private void placePeriod(long residual, long minuend) {
        startPeriod = residuals.lastIndexOf(residual) + 1;
        minuends.add(minuend / 10);
    }

    private boolean isNeedDot(int numberOfNumeratorDigit) {
        return (!hasDot()) && (numberOfNumeratorDigit >= numerator.length());
    }

    private boolean isFoundIrrationalAnswer(long residual) {
        return (hasDot() && (residuals.lastIndexOf(residual) >= dotIndex));
    }

    private int getLengthOf(long value) {
        return String.valueOf(value).length();
    }


    private boolean hasDot() {
        return (dotIndex != -1);
    }

    private void correctSomePeriodic() {

        // костыль для частного случая
        if (startPeriod > 0
                && startPeriod != dotIndex
                && answerParts.get(startPeriod - 1).equals(answerParts.get(answerParts.size() - 1))) {
            startPeriod--;
            answerParts.remove(answerParts.size() - 1);
            subtrahends.remove(subtrahends.size() - 1);
            minuends.remove(minuends.size() - 1);
            minuends.set(minuends.size() - 1, minuends.get(minuends.size() - 1) / 10);
            offsets.remove(offsets.size() - 1);
        }
    }


}
