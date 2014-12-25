package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Subtrahend extends Value {

    public Subtrahend(AnswerPart answerPart, SourceValue denominator) {
        super(answerPart.getValueLong() * denominator.getValueLong());
    }
}
