package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class ValuesList {
    private Values<Residual> residuals = new Values<>();
    private Values<Minuend> minuends = new Values<>();
    private Values<Subtrahend> subtrahends = new Values<>();
    private Values<AnswerPart> answerParts = new Values<>();

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

    void removeFirstAll() {
        answerParts.removeFirst();
        residuals.removeFirst();
        minuends.removeFirst();
        subtrahends.removeFirst();
    }
}
