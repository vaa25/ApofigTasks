package com.goit.apofig.longDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Vlasov
 */
class Data {
    private List<Long> residuals = new ArrayList<>();   // разности
    private List<Long> minuends = new ArrayList<>();    // уменьшаемые
    private List<Long> subtrahends = new ArrayList<>(); // вычитаемые
    private List<Long> answerParts = new ArrayList<>(); // цифры ответа
    private List<Integer> offsets = new ArrayList<>();  // отступы в вычислении столбиком
    private int dotIndex;                               // индекс десятичной запятой
    private int startPeriod;                            // индекс начала периода
    private double numerator;                           // числитель
    private double denominator;                         // знаменатель
    private long long1;                                 // числитель, приведенный к целому числу
    private long long2;                                 // знаменатель, приведенный к целому числу
    private String string1;                             // числитель, приведенный к целому числу в символьном виде
    private String string2;                             // знаменатель, приведенный к целому числу в символьном виде

    Data(double numerator, double denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    String getString1() {
        return string1;
    }

    String getString2() {
        return string2;
    }

    List<Long> getMinuends() {
        return minuends;
    }

    List<Long> getSubtrahends() {
        return subtrahends;
    }

    List<Long> getAnswerParts() {
        return answerParts;
    }

    List<Integer> getOffsets() {
        return offsets;
    }

    int getDotIndex() {
        return dotIndex;
    }

    int getStartPeriod() {
        return startPeriod;
    }

    void calculate() {
        cast();
        calculate1();
        correctSomePeriodic();
    }

    private void cast() {
        getRidOfTheFraction();
        castToLong();
        castToString();
    }

    private void castToString() {
        string1 = String.valueOf(long1);
        string2 = String.valueOf(long2);
    }

    private void castToLong() {
        long1 = ((Double) numerator).longValue();
        long2 = ((Double) denominator).longValue();
    }

    private void getRidOfTheFraction() {
        String decimalPart1 = Double.toString(numerator).split("\\.")[1];
        String decimalPart2 = Double.toString(denominator).split("\\.")[1];

        if ((!"0".equals(decimalPart1)) || (!"0".equals(decimalPart2))) {
            int koef = Math.max(decimalPart1.length(), decimalPart2.length());
            for (int i = 0; i < koef; i++) {
                numerator *= 10;
                denominator *= 10;
            }
        }
    }

    private void calculate1() {
        int len2;                   // число цифр в знаменателе
        int len1;                   // число цифр в числителе
        int offset = 0;             // отступ
        int digitOfNumeratorNumber; // номер цифры числителя, которая добавляется к разности
        long minuend;               // уменьшаемое
        long answerPart;            // цифра ответа
        long subtrahend;            // вычитаемое
        long residual;              // разность
        dotIndex = -1;
        startPeriod = -1;
        len2 = string2.length();
        len1 = string1.length();
        digitOfNumeratorNumber = Math.min(len1, len2);
        minuend = (len2 < len1) ? Long.valueOf(string1.substring(0, len2)) : long1;
        do {
            answerPart = minuend / long2;
            subtrahend = answerPart * long2;
            residual = minuend - subtrahend;
            offset += getLengthOf(minuend) - getLengthOf(residual);
            subtrahends.add(subtrahend);
            minuends.add(minuend);
            answerParts.add(answerPart);
            offsets.add(offset);
            minuend = (digitOfNumeratorNumber >= len1)
                    ? residual * 10
                    : residual * 10 + getDigitOfNumerator(digitOfNumeratorNumber);
            if (hasDot() && (residuals.lastIndexOf(residual) >= dotIndex)) {
                startPeriod = residuals.lastIndexOf(residual) + 1;
                minuends.add(minuend / 10);
                break;
            }
            residuals.add(residual);
            if ((!hasDot()) && (digitOfNumeratorNumber >= len1)) {
                dotIndex = answerParts.size();
            }
            digitOfNumeratorNumber++;
        } while ((residual != 0) || (digitOfNumeratorNumber <= len1));
    }

    private int getLengthOf(long value) {
        return String.valueOf(value).length();
    }

    private Long getDigitOfNumerator(int digitNumber) {
        return Long.valueOf(string1.substring(digitNumber, digitNumber + 1));
    }

    private boolean hasDot() {
        return (dotIndex != -1);
    }

    private void correctSomePeriodic() {

        // костыль для частного случая
        if (startPeriod > 0
                && startPeriod != dotIndex
                && answerParts.get(startPeriod - 1).equals(answerParts.get(answerParts.size() - 1))) {
            startPeriod--;
            answerParts.remove(answerParts.size() - 1);
            subtrahends.remove(subtrahends.size() - 1);
            minuends.remove(minuends.size() - 1);
            minuends.set(minuends.size() - 1, minuends.get(minuends.size() - 1) / 10);
            offsets.remove(offsets.size() - 1);
        }
    }


}
