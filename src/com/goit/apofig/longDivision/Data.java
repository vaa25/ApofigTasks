package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
class Data {
    private final int ABSENT = -1;
    private Values<Residual> residuals = new Values<>();
    private Values<Minuend> minuends = new Values<>();
    private Values<Subtrahend> subtrahends = new Values<>();
    private Values<AnswerPart> answerParts = new Values<>();
    private int dotIndex;
    private int startPeriod;
    private Numerator numerator;
    private SourceValue denominator;

    Data(double numerator, double denominator) {
        this.numerator = new Numerator(numerator);
        this.denominator = new SourceValue(denominator);
        SourceValue.cast(this.numerator, this.denominator);
    }

    public SourceValue getNumerator() {
        return numerator;
    }

    public SourceValue getDenominator() {
        return denominator;
    }

    Values<Minuend> getMinuends() {
        return minuends;
    }

    Values<Residual> getResiduals() {
        return residuals;
    }

    Values<Subtrahend> getSubtrahends() {
        return subtrahends;
    }

    Values<AnswerPart> getAnswerParts() {
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
        if (answerParts.get(0).getValueLong() == 0 && (dotIndex > 1)) {
            answerParts.removeFirst();
            residuals.removeFirst();
            minuends.removeFirst();
            subtrahends.removeFirst();
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
        numerator.setDigitCounter(denominator);
        Minuend minuend = numerator.getFirstMinuend(denominator);
        do {
            AnswerPart answerPart = new AnswerPart(minuend, denominator);
            Subtrahend subtrahend = new Subtrahend(answerPart, denominator);
            Residual residual = new Residual(minuend, subtrahend);
            setPeriodIfNeed(residual);
            minuend = getNextMinuend(residual);
            addValuesToLists(minuend, answerPart, subtrahend, residual);
            setDotIfNeed();
            numerator.increaseDigitCounter();
        } while (!hasIrrationalAnswer() && !hasRationalAnswer());
    }

    private void setDotIfNeed() {
        if (isNeedDot()) {
            setDot();
        }
    }

    private void setPeriodIfNeed(Residual residual) {
        if (isIrrationalSymptomDetected(residual)) {
            setPeriod(residual);
        }
    }

    private boolean hasRationalAnswer() {
        return (isRationalSymptomDetected() && numerator.doesAllNumeratorDigitsWereInWork());
    }

    private Minuend getNextMinuend(Residual residual) {
        if (hasIrrationalAnswer()) {
            return new Minuend(residual.getValueLong());
        } else {
            return numerator.getNextMinuend(residual);
        }
    }

    boolean isRationalSymptomDetected() {
        return (residuals.isLastZero());
    }

    private void addValuesToLists(Minuend minuend, AnswerPart answerPart, Subtrahend subtrahend, Residual residual) {
        subtrahends.add(subtrahend);
        minuends.add(minuend);
        answerParts.add(answerPart);
        residuals.add(residual);
    }
    private void setDot() {
        dotIndex = answerParts.size();
    }

    private void setPeriod(Residual residual) {
        startPeriod = residuals.lastIndexOf(residual) + 1;
    }

    private boolean isNeedDot() {
        return (!hasDot()) && (numerator.isNeedDot());
    }

    private boolean isIrrationalSymptomDetected(Residual residual) {
        return (hasDot() && (residuals.lastIndexOf(residual) >= dotIndex - 1));
    }

    private boolean hasDot() {
        return (dotIndex != ABSENT);
    }

}
