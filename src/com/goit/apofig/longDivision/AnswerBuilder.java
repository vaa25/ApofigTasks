package com.goit.apofig.longDivision;

import java.util.List;

/**
 * @author Alexander Vlasov
 */
class AnswerBuilder {
    private Data data;

    AnswerBuilder(Data data) {
        this.data = data;
        setData(data);
    }

    void setData(Data data) {
        this.data = data;
    }

    String buildLongAnswer() {
        List<Long> minuends = data.getMinuends();
        List<Long> subtrahends = data.getSubtrahends();
        List<Long> answerParts = data.getAnswerParts();
        List<Integer> offsets = data.getOffsets();
        int startPeriod = data.getStartPeriod();
        String string2 = data.getString2();
        String string1 = data.getString1();
        int l3 = subtrahends.get(0).toString().length();
        int len2 = string2.length();
        int len1 = string1.length();
        StringBuilder result = new StringBuilder();
        long lastMinuend = minuends.get(minuends.size() - 1);
        String offs1 = getOffset((len2 > len1) ? Math.max(len1, l3) - Math.min(len1, l3) : 0, ' ');
        String answer = buildAnswer();
        result.append(' ').append(string1).append(offs1).append('|').append(string2).append('\n');
        result.append('-').append(getOffset(len1, ' ')).append(offs1).append('+')
                .append(getOffset(Math.max(answer.length(), string2.length()), '-')).append('\n');
        result.append(' ').append(subtrahends.get(0)).append(getOffset(len1 - l3, ' '))
                .append('|').append(answer).append('\n');
        result.append(' ').append(getOffset(l3, '-')).append('\n');
        for (int i = 1; i < answerParts.size(); i++) {
            if (subtrahends.get(i) > 0) {
                String offset2 = getOffset(offsets.get(i - 1), ' ');
                String offset4 = offset2 + " "
                        + getOffset(minuends.get(i).toString().length() - subtrahends.get(i).toString().length(), ' ');
                result.append(offset2).append(' ').append(minuends.get(i)).append('\n');
                result.append(offset2).append('\n');
                result.append(offset4).append(subtrahends.get(i)).append('\n');
                result.append(offset4).append(getOffset(String.valueOf(subtrahends.get(i)).length(), '-')).append('\n');
            }
        }
        result.append(getOffset(offsets.get(offsets.size() - 1) + 1, ' '))
                .append((startPeriod > 0) ? lastMinuend : lastMinuend - subtrahends.get(subtrahends.size() - 1));
        return result.toString();
    }

    String buildAnswer() {
        List<Long> answerParts = data.getAnswerParts();
        int dotIndex = data.getDotIndex();
        int startPeriod = data.getStartPeriod();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < answerParts.size(); i++) {
            if (i == dotIndex) {
                answer.append('.');
            }
            if (i == startPeriod) {
                answer.append('(');
            }
            answer.append(answerParts.get(i));
        }
        if (startPeriod != -1) {
            answer.append(')');
        }
        return answer.toString();
    }

    private String getOffset(int i, char value) {
        StringBuilder result = new StringBuilder();

        for (int j = 0; j < i; j++) {
            result.append(value);
        }
        return result.toString();
    }
}
