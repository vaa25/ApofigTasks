package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class AnswerPart extends Value {

    public AnswerPart(Minuend minuend, SourceValue denominator) {
        super(minuend.getValueLong() / denominator.getValueLong());
    }
}
