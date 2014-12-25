package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Subtrahend extends Value {
    Subtrahend(long value) {
        super(value);
    }

    public Subtrahend(AnswerPart answerPart, SourceValue denominator) {
        super(answerPart.getValueLong() * denominator.getValueLong());
    }
}
