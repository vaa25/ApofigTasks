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
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < answerParts.size(); i++) {
            if (i == data.getDotIndex()) {
                result.append('.');
            }
            if (i == data.getStartPeriodIndex()) {
                result.append('(');
            }
            result.append(answerParts.get(i));
        }
        if (data.hasIrrationalAnswer()) {
            result.append(')');
        }
        return result.toString();
    }

    String buildLongAnswer() {
        result = new StringBuilder();
        List<Long> subtrahends = data.getSubtrahends();
        List<Long> minuends = data.getMinuends();
        List<Integer> offsets = data.getOffsets();
        buildFirstThreeStrokes();
        buildCascades(offsets, minuends, subtrahends);
        buildLastResidual();
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

    private void buildLastResidual() {
        append(getOffset(getLastOffset(data.getOffsets()) + 1));
        if (data.hasIrrationalAnswer()) {
            append(getLastValue(data.getResiduals()).toString());
        } else if (data.isRationalSymptomDetected()) {
            append("0");
        }
    }

    private Integer getLastOffset(List<Integer> values) {
        return values.get(values.size() - 1);
    }

    private Long getLastValue(List<Long> values) {
        return values.get(values.size() - 1);
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

    private void buildFirstThreeStrokes() {
        String subtrahend = data.getSubtrahends().get(0).toString();
        String numerator = data.getNumerator().getString();
        String denominator = data.getDenominator().getString();
        String firstLineOffset = getOffset((denominator.length() > numerator.length())
                ? Math.max(numerator.length(), subtrahend.length()) - Math.min(numerator.length(), subtrahend.length())
                : 0);
        append(" ", numerator, firstLineOffset, "|", denominator, "\n");
        String answer = buildAnswer();
        append("-", getOffset(numerator.length()), firstLineOffset,
                "+", getLine(Math.max(answer.length(), denominator.length())), "\n");
        append(" ", subtrahend, getOffset(numerator.length() - denominator.length()), "|", answer, "\n");
        append(" ", getLine(subtrahend.length()), "\n");
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
