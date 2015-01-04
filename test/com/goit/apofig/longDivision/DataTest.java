package com.goit.apofig.longDivision;

import junit.framework.TestCase;
import org.junit.Test;

public class DataTest extends TestCase {
    private final int ABSENT = Data.ABSENT;
    private Data data;

    @Test
    public void testData() throws Exception {
        //given
        Numerator numerator = new Numerator(2930);
        SourceValue denominator = new SourceValue(2.45);
        SourceValue.cast(numerator, denominator);

        //when
        data = new Data(2930, 2.45);

        //then
        assertEquals(numerator, data.getNumerator());
        assertEquals(denominator, data.getDenominator());
    }

    @Test
    public void testCalculate() throws Exception {
        //given
        givenData(2930, 2.45);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(4);
        thenDotIndex(4);
        thenAnswerParts("[1, 1, 9, 5, 9, 1, 8, 3, 6, 7, 3, 4, 6, 9, 3, 8, 7, 7, 5, 5, 1, 0, 2, 0, 4, 0, 8, 1, 6, 3," +
                " 2, 6, 5, 3, 0, 6, 1, 2, 2, 4, 4, 8, 9, 7, 9, 5]");
        thenResiduals("[48, 235, 145, 225, 45, 205, 90, 165, 180, 85, 115, 170, 230, 95, 215, 190, 185, 135, 125, " +
                "25, 5, 50, 10, 100, 20, 200, 40, 155, 80, 65, 160, 130, 75, 15, 150, 30, 55, 60, 110, 120, 220," +
                " 240, 195, 235, 145, 225]");
        thenMinuends("[293, 480, 2350, 1450, 2250, 450, 2050, 900, 1650, 1800, 850, 1150, 1700, 2300, 950, 2150, " +
                "1900, 1850, 1350, 1250, 250, 50, 500, 100, 1000, 200, 2000, 400, 1550, 800, 650, 1600, 1300, 750," +
                " 150, 1500, 300, 550, 600, 1100, 1200, 2200, 2400, 1950, 2350, 1450]");
        thenSubtrahends("[245, 245, 2205, 1225, 2205, 245, 1960, 735, 1470, 1715, 735, 980, 1470, 2205, 735, 1960, " +
                "1715, 1715, 1225, 1225, 245, 0, 490, 0, 980, 0, 1960, 245, 1470, 735, 490, 1470, 1225, 735," +
                " 0, 1470, 245, 490, 490, 980, 980, 1960, 2205, 1715, 2205, 1225]");

        //given
        givenData(4, 2);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(ABSENT);
        thenDotIndex(1);
        thenAnswerParts("[2]");
        thenResiduals("[0]");
        thenMinuends("[4]");
        thenSubtrahends("[4]");

        //given
        givenData(40, 2);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(ABSENT);
        thenDotIndex(2);
        thenAnswerParts("[2, 0]");
        thenResiduals("[0, 0]");
        thenMinuends("[4, 0]");
        thenSubtrahends("[4, 0]");

        //given
        givenData(2, 40);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(ABSENT);
        thenDotIndex(1);
        thenAnswerParts("[0, 0, 5]");
        thenResiduals("[2, 20, 0]");
        thenMinuends("[2, 20, 200]");
        thenSubtrahends("[0, 0, 200]");

        //given
        givenData(190, 4);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(ABSENT);
        thenDotIndex(2);
        thenAnswerParts("[4, 7, 5]");
        thenResiduals("[3, 2, 0]");
        thenMinuends("[19, 30, 20]");
        thenSubtrahends("[16, 28, 20]");

        //given
        givenData(2930, 24);

        //when
        whenCalculate();

        //then
        thenStartPeriodIndex(5);
        thenDotIndex(3);
        thenAnswerParts("[1, 2, 2, 0, 8, 3]");
        thenResiduals("[5, 5, 2, 20, 8, 8]");
        thenMinuends("[29, 53, 50, 20, 200, 80]");
        thenSubtrahends("[24, 48, 48, 0, 192, 72]");
    }

    private void givenData(double numerator, double denominator) {
        data = new Data(numerator, denominator);
    }

    private void whenCalculate() {
        data.calculate();
    }

    private void thenStartPeriodIndex(int startPeriodIndex) {
        assertEquals(startPeriodIndex, data.getStartPeriodIndex());
    }

    private void thenSubtrahends(String subtrahends) {
        assertEquals(subtrahends, data.getSubtrahends().toString());
    }

    private void thenMinuends(String minuends) {
        assertEquals(minuends, data.getMinuends().toString());
    }

    private void thenResiduals(String residuals) {
        assertEquals(residuals, data.getResiduals().toString());
    }

    private void thenAnswerParts(String answerParts) {
        assertEquals(answerParts, data.getAnswerParts().toString());
    }

    private void thenDotIndex(int dotIndex) {
        assertEquals(dotIndex, data.getDotIndex());
    }

    @Test
    public void testHasIrrationalAnswer() throws Exception {
        testHasIrrationalAnswer(2930, 2.45, true);
        testHasIrrationalAnswer(4, 2, false);
        testHasIrrationalAnswer(2, 40, false);
        testHasIrrationalAnswer(190, 4, false);
        testHasIrrationalAnswer(2930, 24, true);
    }

    private void testHasIrrationalAnswer(double numerator, double denominator, boolean expected) {
        //given
        givenData(numerator, denominator);

        //when
        whenCalculate();

        //then
        thenHasIrrationalAnswer(expected);
    }

    private void thenHasIrrationalAnswer(boolean expected) {
        assertEquals(expected, data.hasIrrationalAnswer());
    }

    @Test
    public void testIsRationalSymptomDetected() throws Exception {
        testIsRationalSymptomDetected(2930, 2.45, false);
        testIsRationalSymptomDetected(4, 2, true);
        testIsRationalSymptomDetected(2, 40, true);
        testIsRationalSymptomDetected(190, 4, true);
        testIsRationalSymptomDetected(2930, 24, false);
    }

    private void testIsRationalSymptomDetected(double numerator, double denominator, boolean expected) {
        //given
        givenData(numerator, denominator);

        //when
        whenCalculate();

        //then
        thenIsRationalSymptomDetected(expected);
    }

    private void thenIsRationalSymptomDetected(boolean expected) {
        assertEquals(expected, data.isRationalSymptomDetected());
    }
}