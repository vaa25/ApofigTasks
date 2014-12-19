package com.goit.apofig.longDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * http://ru.onlinemschool.com/math/assistance/number_theory/division/
 *
 * @author Alexander Vlasov
 */
public class LongDivision {
    public static void main(String[] args) {
        LongDivision longDivision = new LongDivision();
        longDivision.start(2930, 2.45);
        longDivision.start(2930, 2.4);
    }

    private void start(double d1, double d2) {
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
        String string1 = String.valueOf(long1);
        String string2 = String.valueOf(long2);
        System.out.println("a = " + string1);
        System.out.println("b = " + string2);
        int exponent10 = 1;
        int exponent2 = string1.length() - string2.length();
        for (int i = 0; i < exponent2; i++) {
            exponent10 *= 10;
        }
        long dotIndex = -1;
        List<Long> разности = new ArrayList<>();
        List<Long> уменьшаемые = new ArrayList<>();
        List<Long> вычитаемые = new ArrayList<>();
        List<Long> answerParts = new ArrayList<>();
        List<Integer> offsets = new ArrayList<>();
        long answerPart = long1 / long2 / exponent10;
        long вычитаемое = answerPart * long2;
        long разность = long1 / exponent10 - вычитаемое;
        String answer = String.valueOf(answerPart);
        int startPeriod = -1;
        int offset = 1;
        exponent2 = string2.length();
        if (разность == 0) {
            вычитаемые.add(вычитаемое);
            уменьшаемые.add(разность);
            answerParts.add(answerPart);
            offsets.add(offset);
        }
        while (разность != 0) {
            разность *= 10;
            if (exponent2 < string1.length()) {
                разность += Long.valueOf(string1.substring(exponent2++, exponent2));
            } else {
                if (dotIndex == -1) {
                    dotIndex = answerParts.size();
                    System.out.println("индекс точки = " + dotIndex);
                }
            }
            long уменьшаемое = разность;
            answerPart = уменьшаемое / long2;
            вычитаемое = answerPart * long2;
            разность = уменьшаемое - вычитаемое;
            offsets.add(offset);
            if (dotIndex != -1 && разности.lastIndexOf(разность) >= dotIndex) {
                startPeriod = разности.lastIndexOf(разность);
                уменьшаемые.add(уменьшаемое / 10);
                break;
            } else {
                уменьшаемые.add(уменьшаемое);
                разности.add(разность);
                answerParts.add(answerPart);
                вычитаемые.add(вычитаемое);
                offset += String.valueOf(уменьшаемое).length() - String.valueOf(разность).length();
            }
            System.out.printf("цифра ответа = %d, уменьшаемое = %d, вычитаемое = %d, разность = %d\n",
                    answerPart, уменьшаемое, вычитаемое, разность);
        }
        for (int i = 0; i < разности.size(); i++) {
            if (i == dotIndex) answer += ".";
            if (i == startPeriod) answer += "(";
            answer += (answerParts.get(i));
        }
        if (startPeriod != -1) answer += ")";
        System.out.println("Answer = " + answer);


        int l1 = string1.length();
        int l3 = вычитаемые.get(0).toString().length();
        String offs1 = getOffset(Math.max(l1, l3) - Math.min(l1, l3), ' ');
        System.out.printf(" %d%s|%d\n", long1, offs1, long2);
        System.out.printf("-" + getOffset(l1, ' ') + offs1 + "+" + getOffset(Math.max(answer.length(), string2.length()), '-') + "\n");
        System.out.printf(" %d" + getOffset(l1 - l3, ' ') + "|%s\n", вычитаемые.get(0), answer);
        System.out.printf(" " + getOffset(l3, '-') + "\n");
        for (int i = 0; i < answerParts.size(); i++) {
            if (вычитаемые.get(i) > 0) {
                String offset2 = getOffset(offsets.get(i), ' ');
                String offset3 = offset2 + " ";
                String offset4 = offset3 + getOffset(уменьшаемые.get(i).toString().length() - вычитаемые.get(i).toString().length(), ' ');
                System.out.println(offset3 + уменьшаемые.get(i));
                System.out.println(offset2 + "-");
                System.out.println(offset4 + вычитаемые.get(i));
                System.out.println(offset4 + getOffset(String.valueOf(вычитаемые.get(i)).length(), '-'));
            }
        }
        if (уменьшаемые.size() > answerParts.size()) {
            String offset2 = getOffset(offsets.get(offsets.size() - 1) + 1, ' ');
            System.out.println(offset2 + уменьшаемые.get(уменьшаемые.size() - 1));
        }
    }

    private String getOffset(int i, char value) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < i; j++) {
            result.append(value);
        }
        return result.toString();
    }
}
