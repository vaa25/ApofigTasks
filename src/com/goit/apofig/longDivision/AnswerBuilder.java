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
        for (int i = 0; i < data.getAnswerParts().size(); i++) {
            appendSymbol(i);
        }
        if (data.hasIrrationalAnswer()) {
            result.append(')');
        }
        return result.toString();
    }

    private void appendSymbol(int i) {
        if (i == data.getDotIndex()) {
            result.append('.');
        }
        if (i == data.getStartPeriodIndex()) {
            result.append('(');
        }
        result.append(data.getAnswerParts().get(i));
    }
}
