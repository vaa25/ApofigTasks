package com.goit.apofig.longDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * http://ru.onlinemschool.com/math/assistance/number_theory/division/
 *
 * @author Alexander Vlasov
 */
public class LongDivision {
    private List<Long> residuals = new ArrayList<>();
    private List<Long> minuends = new ArrayList<>();
    private List<Long> subtrahends = new ArrayList<>();
    private List<Long> answerParts = new ArrayList<>();
    private List<Integer> offsets = new ArrayList<>();
    private String answer = "";
    private String longAnswer = "";
    private String string1, string2;
    private int dotIndex;
    private int startPeriod;
    public static void main(String[] args) {
        LongDivision longDivision = new LongDivision();
        longDivision.calculate(2930, 2.45);
//        longDivision.calculate(4, 2);
//        longDivision.calculate(40, 2);
//        longDivision.calculate(2, 40);
//        longDivision.calculate(2930, 24);
        System.out.println(longDivision.longAnswer);
    }

    public String getAnswer() {
        return answer;
    }

    public String getLongAnswer() {
        return longAnswer;
    }

    private void calculate(double d1, double d2) {
        residuals.clear();
        minuends.clear();
        subtrahends.clear();
        answerParts.clear();
        offsets.clear();


        dotIndex = -1;
        startPeriod = -1;

        String decimalPart1 = Double.toString(d1).split("\\.")[1];
        String decimalPart2 = Double.toString(d2).split("\\.")[1];
        if (!"0".equals(decimalPart1) || !"0".equals(decimalPart2)) {
            int koef = Math.max(decimalPart1.length(), decimalPart2.length());
            for (int i = 0; i < koef; i++) {
                d1 *= 10;
                d2 *= 10;
            }
        }
        long long1 = ((Double) d1).longValue();
        long long2 = ((Double) d2).longValue();
        string1 = String.valueOf(long1);
        string2 = String.valueOf(long2);
        int len2 = string2.length();
        int len1 = string1.length();
        int offset = 0;
        int digitNumber1 = Math.min(len1, len2);
        long minuend = len2 < len1 ? Long.valueOf(string1.substring(0, len2)) : long1;
        long answerPart;
        long subtrahend;
        long residual;
        do {
            answerPart = minuend / long2;
            subtrahend = answerPart * long2;
            residual = minuend - subtrahend;
            offset += String.valueOf(minuend).length() - String.valueOf(residual).length();
            subtrahends.add(subtrahend);
            minuends.add(minuend);
            answerParts.add(answerPart);
            offsets.add(offset);
            minuend = digitNumber1 >= len1 ?
                    residual * 10 :
                    residual * 10 + Long.valueOf(string1.substring(digitNumber1, digitNumber1 + 1));
            if (dotIndex != -1 && residuals.lastIndexOf(residual) >= dotIndex) {
                startPeriod = residuals.lastIndexOf(residual) + 1;
                minuends.add(minuend / 10);
                break;
            }
            residuals.add(residual);
            if (dotIndex == -1 && digitNumber1 >= len1) dotIndex = answerParts.size();
            digitNumber1++;
        } while (residual != 0 || digitNumber1 <= len1);

        correctSomePeriodic();
        answer = buildAnswer();
        longAnswer = buildLongAnswer();
    }

    private void correctSomePeriodic() {
        // костыль для частного случая
        if (startPeriod > 0 && startPeriod != dotIndex && answerParts.get(startPeriod - 1) == answerParts.get(answerParts.size() - 1)) {
            startPeriod--;
            answerParts.remove(answerParts.size() - 1);
            subtrahends.remove(subtrahends.size() - 1);
            minuends.remove(minuends.size() - 1);
            minuends.set(minuends.size() - 1, minuends.get(minuends.size() - 1) / 10);
            offsets.remove(offsets.size() - 1);
        }
    }

    private String buildLongAnswer() {
        longAnswer = "";
        StringBuilder result = new StringBuilder();
        int l3 = subtrahends.get(0).toString().length();
        int len2 = string2.length();
        int len1 = string1.length();
        String offs1 = getOffset(len2 > len1 ? Math.max(len1, l3) - Math.min(len1, l3) : 0, ' ');
        result.append(' ').append(string1).append(offs1).append('|').append(string2).append('\n');
        result.append('-').append(getOffset(len1, ' ')).append(offs1).append('+').append(getOffset(Math.max(answer.length(), string2.length()), '-')).append('\n');
        result.append(' ').append(subtrahends.get(0)).append(getOffset(len1 - l3, ' ')).append('|').append(answer).append('\n');
        result.append(' ').append(getOffset(l3, '-')).append('\n');
        for (int i = 1; i < answerParts.size(); i++) {
            if (subtrahends.get(i) > 0) {
                String offset2 = getOffset(offsets.get(i - 1), ' ');
                String offset4 = offset2 + " " + getOffset(minuends.get(i).toString().length() - subtrahends.get(i).toString().length(), ' ');
                result.append(offset2).append(' ').append(minuends.get(i)).append('\n');
                result.append(offset2).append('\n');
                result.append(offset4).append(subtrahends.get(i)).append('\n');
                result.append(offset4).append(getOffset(String.valueOf(subtrahends.get(i)).length(), '-')).append('\n');
            }
        }
        long lastMinuend = minuends.get(minuends.size() - 1);
        result.append(getOffset(offsets.get(offsets.size() - 1) + 1, ' ')).append(startPeriod > 0 ? lastMinuend : lastMinuend - subtrahends.get(subtrahends.size() - 1));
        return result.toString();
    }

    private String buildAnswer() {
        String answer = "";
        for (int i = 0; i < answerParts.size(); i++) {
            if (i == dotIndex) answer += ".";
            if (i == startPeriod) answer += "(";
            answer += (answerParts.get(i));
        }
        if (startPeriod != -1) answer += ")";
        return answer;
    }

    private String getOffset(int i, char value) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < i; j++) {
            result.append(value);
        }
        return result.toString();
    }
}
