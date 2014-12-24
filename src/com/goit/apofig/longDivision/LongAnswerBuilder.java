package com.goit.apofig.longDivision;

import java.util.List;

/**
 * @author Alexander Vlasov
 */
class LongAnswerBuilder {
    private Data data;
    private StringBuilder result;

    LongAnswerBuilder(Data data) {
        this.data = data;
    }

    String buildLongAnswer() {
        result = new StringBuilder();
        buildFirstThreeStrokes();
        buildLastResidual(buildCascades());
        return result.toString();
    }

    private int buildCascades() {
        int offset = 1;
        for (int i = 1; i < data.getAnswerParts().size(); i++) {
            String subtrahend = data.getSubtrahends().get(i).toString();
            String minuend = data.getMinuends().get(i - 1).toString();
            if (!"0".equals(subtrahend)) {
                buildCascade(subtrahend, minuend, getOffset(offset));
            }
            offset += (minuend.length() - data.getResiduals().get(i).toString().length());
        }
        return offset;
    }

    private void buildLastResidual(int lastOffsetIndex) {
        append(getOffset(lastOffsetIndex + 1));
        if (data.hasIrrationalAnswer()) {
            append(getLastValue(data.getResiduals()).toString());
        } else if (data.isRationalSymptomDetected()) {
            append("0");
        }
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
        String answer = new AnswerBuilder(data).buildAnswer();
        append("-", getOffset(numerator.length()), firstLineOffset,
                "+", getLine(Math.max(answer.length(), denominator.length())), "\n");
        append(" ", subtrahend);
        append(getOffset(numerator.length() - data.getSubtrahends().get(0).toString().length()), "|", answer, "\n");
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
