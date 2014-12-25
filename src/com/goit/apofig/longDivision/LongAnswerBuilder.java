package com.goit.apofig.longDivision;

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
        buildFirstFourStrokes();
        buildLastResidual(buildCascades());
        return result.toString();
    }

    private int buildCascades() {
        int offset = 1;
        for (int i = 1; i < data.getAnswerParts().size(); i++) {
            String subtrahend = data.getSubtrahends().get(i).toString();
            String minuend = data.getMinuends().get(i - 1).toString();
            if (!"0".equals(subtrahend)) {
                buildCascade(subtrahend, minuend, OffsetBuilder.getOffset(offset));
            }
            offset += (minuend.length() - data.getResiduals().get(i).length());
        }
        return offset;
    }

    private void buildLastResidual(int lastOffsetIndex) {
        append(OffsetBuilder.getOffset(lastOffsetIndex + 1));
        if (data.hasIrrationalAnswer()) {
            append(data.getResiduals().getLast().toString());
        } else if (data.isRationalSymptomDetected()) {
            append("0");
        }
    }

    private void buildCascade(String subtrahend, String minuend, String offset) {
        String offset2 = offset + " " + OffsetBuilder.getOffset(minuend.length() - subtrahend.length());
        buildMenuend(offset, minuend);
        buildMinus(offset);
        buildSubtrahend(offset2, subtrahend);
        buildLine(offset2, subtrahend.length());
    }

    private void buildLine(String offset, int length) {
        append(offset, OffsetBuilder.getLine(length), "\n");
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

    private void buildFirstFourStrokes() {
        String subtrahend = data.getSubtrahends().get(0).toString();
        String numerator = data.getNumerator().toString();
        String denominator = data.getDenominator().toString();
        String firstLineOffset = OffsetBuilder.getOffset((denominator.length() > numerator.length())
                ? Math.max(numerator.length(), subtrahend.length()) - Math.min(numerator.length(), subtrahend.length())
                : 0);
        append(" ", numerator, firstLineOffset, "|", denominator, "\n");
        String answer = new AnswerBuilder(data).buildAnswer();
        append("-", OffsetBuilder.getOffset(numerator.length()), firstLineOffset,
                "+", OffsetBuilder.getLine(Math.max(answer.length(), denominator.length())), "\n");
        append(" ", subtrahend);
        append(OffsetBuilder.getOffset(numerator.length() - data.getSubtrahends().get(0).length()), "|", answer, "\n");
        append(" ", OffsetBuilder.getLine(subtrahend.length()), "\n");
    }

    private void append(String... strings) {
        for (String s : strings) {
            result.append(s);
        }
    }
}
