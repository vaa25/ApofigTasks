package com.goit.apofig.longDivision;

import java.util.ArrayList;

/**
 * @author Alexander Vlasov
 */
public class Values<T extends Value> extends ArrayList<T> {

    public void removeFirst() {
        remove(0);
    }

    public T getLast() {
        return get(size() - 1);
    }

    public boolean isLastZero() {
        return get(size() - 1).getValueLong() == 0;
    }

}
