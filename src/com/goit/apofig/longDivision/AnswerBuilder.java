package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class AnswerBuilder {
    private Data data;
    private StringBuilder result;
    AnswerBuilder(Data data) {
        this.data = data;
    }
    String buildAnswer() {
        result = new StringBuilder();
        appendSymbols();
        appendClosingBracketIfNeed();
        return result.toString();
    }

    private void appendClosingBracketIfNeed() {
        if (data.hasIrrationalAnswer()) {
            result.append(')');
        }
    }

    private void appendSymbols() {
        for (int i = 0; i < data.getAnswerParts().size(); i++) {
            appendSymbol(i);
        }
    }

    private void appendSymbol(int i) {
        appendDotIfNeed(i);
        appendOpeningBracketIfNeed(i);
        result.append(data.getAnswerParts().get(i));
    }

    private void appendOpeningBracketIfNeed(int i) {
        if (i == data.getStartPeriodIndex()) {
            result.append('(');
        }
    }

    private void appendDotIfNeed(int i) {
        if (i == data.getDotIndex()) {
            result.append('.');
        }
    }
}
