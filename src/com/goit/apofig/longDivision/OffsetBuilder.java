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

    private static String getCharOffset(int length, char symbol) {
        StringBuilder result = new StringBuilder();

        for (int j = 0; j < length; j++) {
            result.append(symbol);
        }
        return result.toString();
    }
}
