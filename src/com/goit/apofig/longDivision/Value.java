package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class Value {
    protected Long valueLong;
    protected String valueString;

    public Value(Long valueLong) {
        this.valueLong = valueLong;
        valueString = valueLong.toString();
    }

    public long getValueLong() {
        return valueLong;
    }

    public String getValueString() {
        return valueString;
    }

    @Override
    public String toString() {
        return valueString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value)) return false;

        Value value = (Value) o;

        if (!valueLong.equals(value.valueLong)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return valueLong.hashCode();
    }
}
