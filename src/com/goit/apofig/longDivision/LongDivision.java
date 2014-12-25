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

    public void calculate(double numerator, double denominator) {
        Data data = new Data(numerator, denominator);
        data.calculate();
        answer = new AnswerBuilder(data).buildAnswer();
        longAnswer = new LongAnswerBuilder(data).buildLongAnswer();
    }


}
