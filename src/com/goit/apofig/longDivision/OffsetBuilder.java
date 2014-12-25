package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class OffsetBuilder {

    static String getLine(int length) {
        return getCharOffset(length, '-');
    }

    static String getOffset(int length) {
        return getCharOffset(length, ' ');
    }

    private static String getCharOffset(int i, char value) {
        StringBuilder result = new StringBuilder();

        for (int j = 0; j < i; j++) {
            result.append(value);
        }
        return result.toString();
    }
}
