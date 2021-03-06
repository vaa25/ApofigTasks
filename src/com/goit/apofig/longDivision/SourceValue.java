package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class SourceValue {
    protected static final int TEN = 10;
    protected long valueLong;
    protected String valueString;
    private double valueOrig;

    SourceValue(double valueOrig) {
        this.valueOrig = valueOrig;
    }


    static void cast(SourceValue value1, SourceValue value2) {
        String decimalPart1 = Double.toString(value1.valueOrig).split("\\.")[1];
        String decimalPart2 = Double.toString(value2.valueOrig).split("\\.")[1];

        if ((!"0".equals(decimalPart1)) || (!"0".equals(decimalPart2))) {
            int koef = Math.max(decimalPart1.length(), decimalPart2.length());
            for (int i = 0; i < koef; i++) {
                value1.valueOrig *= TEN;
                value2.valueOrig *= TEN;
            }
        }
        value1.cast();
        value2.cast();
    }

    static int getMinLength(SourceValue value1, SourceValue value2) {
        return Math.min(value1.length(), value2.length());
    }

    private void cast() {
        castToLong();
        castToString();
    }

    private void castToString() {
        valueString = String.valueOf(valueLong);
    }

    private void castToLong() {
        valueLong = ((Double) valueOrig).longValue();
    }

    int length() {
        return valueString.length();
    }

    long getValueLong() {
        return valueLong;
    }

    public String toString() {
        return valueString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SourceValue)) return false;

        SourceValue that = (SourceValue) o;

        if (valueLong != that.valueLong) return false;
        if (Double.compare(that.valueOrig, valueOrig) != 0) return false;
        if (!valueString.equals(that.valueString)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (valueLong ^ (valueLong >>> 32));
        result = 31 * result + valueString.hashCode();
        temp = Double.doubleToLongBits(valueOrig);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
