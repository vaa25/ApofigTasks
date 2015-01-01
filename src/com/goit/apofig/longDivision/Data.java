package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
class Data {
    public static final int ABSENT = -1;
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

    public Numerator getNumerator() {
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
        correctUnnecessaryFirstZero();
    }

    private void correctUnnecessaryFirstZero() {
        if (hasUnnecessaryFirstZero()) {
            removeUnnecessaryFirstZeroData();
        }
    }

    private void removeUnnecessaryFirstZeroData() {
        answerParts.removeFirst();
        residuals.removeFirst();
        minuends.removeFirst();
        subtrahends.removeFirst();
        moveDotIndexLeftIfNeed();
        moveStartPeriodLeftIfNeed();
    }

    private void moveDotIndexLeftIfNeed() {
        if (hasDot()) {
            dotIndex--;
        }
    }

    private void moveStartPeriodLeftIfNeed() {
        if (hasIrrationalAnswer()) {
            startPeriod--;
        }
    }

    private boolean hasUnnecessaryFirstZero() {
        return answerParts.get(0).getValueLong() == 0 && (dotIndex > 1);
    }

    boolean hasIrrationalAnswer() {
        return startPeriod != ABSENT;
    }

    private void calculate1() {
        numerator.setDigitCounter(denominator);
        Minuend minuend = numerator.getFirstMinuend(denominator);
        do {
            minuend = calculateCascade(minuend);
        } while (!hasIrrationalAnswer() && !hasRationalAnswer());
    }

    private Minuend calculateCascade(Minuend minuend) {
        AnswerPart answerPart = new AnswerPart(minuend, denominator);
        Subtrahend subtrahend = new Subtrahend(answerPart, denominator);
        Residual residual = new Residual(minuend, subtrahend);
        setPeriodIfNeed(residual);
        addValuesToLists(minuend, answerPart, subtrahend, residual);
        minuend = getNextMinuend(residual);
        setDotIfNeed();
        numerator.increaseDigitCounter();
        return minuend;
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
