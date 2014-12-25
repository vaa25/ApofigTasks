package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Residual extends Value {
    Residual(long value) {
        super(value);
    }

    public Residual(Minuend minuend, Subtrahend subtrahend) {
        super(minuend.getValueLong() - subtrahend.getValueLong());
    }

}
