package com.goit.apofig.longDivision;

import java.util.List;

/**
 * @author Alexander Vlasov
 */
class AnswerBuilder {
    private Data data;
    private StringBuilder result;
    AnswerBuilder(Data data) {
        this.data = data;
        setData(data);
    }

    void setData(Data data) {
        this.data = data;
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

    String buildLongAnswer() {
        result = new StringBuilder();
        List<Long> subtrahends = data.getSubtrahends();
        List<Long> minuends = data.getMinuends();
        List<Integer> offsets = data.getOffsets();
        buildFirstThreeStrokes(subtrahends.get(0).toString());
        buildCascades(offsets, minuends, subtrahends);
        buildLastMinuend(getLastOffset(offsets), getLastValue(minuends), getLastValue(subtrahends));
        return result.toString();
    }

    private void buildCascades(List<Integer> offsets, List<Long> minuends, List<Long> subtrahends) {
        for (int i = 1; i < data.getAnswerParts().size(); i++) {
            String subtrahend = subtrahends.get(i).toString();
            if (!"0".equals(subtrahend)) {
                buildCascade(subtrahend, minuends.get(i).toString(), getOffset(offsets.get(i - 1)));
            }
        }
    }

    private void buildLastMinuend(int offset, Long lastMinuend, Long subtrahend) {
        append(getOffset(offset + 1),
                hasPeriod()
                        ? lastMinuend.toString()
                        : ((Long) (lastMinuend - subtrahend)).toString());
    }

    private int getLastOffset(List<Integer> values) {
        return values.get(values.size() - 1);
    }

    private long getLastValue(List<Long> values) {
        return values.get(values.size() - 1);
    }

    private boolean hasPeriod() {
        return data.getStartPeriod() > 0;
    }

    private void buildCascade(String subtrahend, String minuend, String offset) {
        String offset2 = offset + " " + getOffset(minuend.length() - subtrahend.length());
        buildMenuend(offset, minuend);
        buildMinus(offset);
        buildSubtrahend(offset2, subtrahend);
        buildLine(offset2, subtrahend.length());
    }

    private void buildLine(String offset, int length) {
        append(offset, getLine(length), "\n");
    }

    private String getOffset(int length) {
        return getOffset(length, ' ');
    }

    private String getLine(int length) {
        return getOffset(length, '-');
    }

    private void buildSubtrahend(String offset, String value) {
        append(offset, value, "\n");
    }

    private void buildMinus(String offset) {
        append(offset, "-\n");
    }

    private void buildMenuend(String offset, String value) {
        append(offset, " ", value, "\n");
    }

    private void buildFirstThreeStrokes(String subtrahend) {
        int subtrahendLength = subtrahend.length();
        int numeratorLength = data.getNumerator().length();
        int denominatorLength = data.getDenominator().length();
        String firstLineOffset = getOffset((denominatorLength > numeratorLength)
                ? Math.max(numeratorLength, subtrahendLength) - Math.min(numeratorLength, subtrahendLength)
                : 0);
        String numerator = data.getNumerator().getString();
        append(" ", numerator, firstLineOffset, "|", data.getDenominator().getString(), "\n");
        String answer = buildAnswer();
        append("-", getOffset(numeratorLength), firstLineOffset,
                "+", getLine(Math.max(answer.length(), denominatorLength)), "\n");
        append(" ", subtrahend, getOffset(numeratorLength - subtrahendLength), "|", answer, "\n");
        append(" ", getLine(subtrahendLength), "\n");
    }

    private void append(String... strings) {
        for (String s : strings) {
            result.append(s);
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
