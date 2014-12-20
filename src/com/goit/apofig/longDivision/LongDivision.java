package com.goit.apofig.longDivision;

/**
 * @author Alexander Vlasov
 */
public class LongDivision {
    private String answer = "";
    private String longAnswer = "";

    public String getAnswer() {
        return (isNoAnswer() ? getNullAnswer() : answer);
    }

    public String getLongAnswer() {
        return (isNoAnswer() ? getNullAnswer() : longAnswer);
    }

    private boolean isNoAnswer() {
        return "".equals(answer);
    }

    private String getNullAnswer() {
        return "Call calculate(numerator, denominator) first";
    }

    public void calculate(double d1, double d2) {
        Data data = new Data(d1, d2);
        data.calculate();
        AnswerBuilder answerBuilder = new AnswerBuilder(data);
        answer = answerBuilder.buildAnswer();
        longAnswer = answerBuilder.buildLongAnswer();
    }


}
