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
    public void testCalculate_2930_24_Subtrahends() {
        givenDataWhenCalculate(2930, 24);
        thenSubtrahends("[24, 48, 48, 0, 192, 72]");
    }

    @Test
    public void testCalculate_2930_24_Minuends() {
        givenDataWhenCalculate(2930, 24);
        thenMinuends("[29, 53, 50, 20, 200, 80]");
    }

    @Test
    public void testCalculate_2930_24_Residuals() {
        givenDataWhenCalculate(2930, 24);
        thenResiduals("[5, 5, 2, 20, 8, 8]");
    }

    @Test
    public void testCalculate_2930_24_AnswerParts() {
        givenDataWhenCalculate(2930, 24);
        thenAnswerParts("[1, 2, 2, 0, 8, 3]");
    }

    @Test
    public void testCalculate_2930_24_DotIndex() {
        givenDataWhenCalculate(2930, 24);
        thenDotIndex(3);
    }

    @Test
    public void testCalculate_2930_24_StartPeriodIndex() {
        givenDataWhenCalculate(2930, 24);
        thenStartPeriodIndex(5);
    }

    @Test
    public void testCalculate_190_4_Subtrahends() {
        givenDataWhenCalculate(190, 4);
        thenSubtrahends("[16, 28, 20]");
    }

    @Test
    public void testCalculate_190_4_Minuends() {
        givenDataWhenCalculate(190, 4);
        thenMinuends("[19, 30, 20]");
    }

    @Test
    public void testCalculate_190_4_Residuals() {
        givenDataWhenCalculate(190, 4);
        thenResiduals("[3, 2, 0]");
    }

    @Test
    public void testCalculate_190_4_AnswerParts() {
        givenDataWhenCalculate(190, 4);
        thenAnswerParts("[4, 7, 5]");
    }

    @Test
    public void testCalculate_190_4_DotIndex() {
        givenDataWhenCalculate(190, 4);
        thenDotIndex(2);
    }

    @Test
    public void testCalculate_190_4_StartPeriodIndex() {
        givenDataWhenCalculate(190, 4);
        thenStartPeriodIndex(ABSENT);
    }

    @Test
    public void testCalculate_2_40_Subtrahends() {
        givenDataWhenCalculate(2, 40);
        thenSubtrahends("[0, 0, 200]");
    }

    @Test
    public void testCalculate_2_40_Minuends() {
        givenDataWhenCalculate(2, 40);
        thenMinuends("[2, 20, 200]");
    }

    @Test
    public void testCalculate_2_40_Residuals() {
        givenDataWhenCalculate(2, 40);
        thenResiduals("[2, 20, 0]");
    }

    @Test
    public void testCalculate_2_40_AnswerParts() {
        givenDataWhenCalculate(2, 40);
        thenAnswerParts("[0, 0, 5]");
    }

    @Test
    public void testCalculate_2_40_DotIndex() {
        givenDataWhenCalculate(2, 40);
        thenDotIndex(1);
    }

    @Test
    public void testCalculate_2_40_StartPeriodIndex() {
        givenDataWhenCalculate(2, 40);
        thenStartPeriodIndex(ABSENT);
    }

    @Test
    public void testCalculate_40_2_Subtrahends() {
        givenDataWhenCalculate(40, 2);
        thenSubtrahends("[4, 0]");
    }

    @Test
    public void testCalculate_40_2_Minuends() {
        givenDataWhenCalculate(40, 2);
        thenMinuends("[4, 0]");
    }

    @Test
    public void testCalculate_40_2_Residuals() {
        givenDataWhenCalculate(40, 2);
        thenResiduals("[0, 0]");
    }

    @Test
    public void testCalculate_40_2_AnswerParts() {
        givenDataWhenCalculate(40, 2);
        thenAnswerParts("[2, 0]");
    }

    @Test
    public void testCalculate_40_2_DotIndex() {
        givenDataWhenCalculate(40, 2);
        thenDotIndex(2);
    }

    @Test
    public void testCalculate_40_2_StartPeriodIndex() {
        givenDataWhenCalculate(40, 2);
        thenStartPeriodIndex(ABSENT);
    }

    @Test
    public void testCalculate_4_2_Subtrahends() {
        givenDataWhenCalculate(4, 2);
        thenSubtrahends("[4]");
    }

    @Test
    public void testCalculate_4_2_Minuends() {
        givenDataWhenCalculate(4, 2);
        thenMinuends("[4]");
    }

    @Test
    public void testCalculate_4_2_Residuals() {
        givenDataWhenCalculate(4, 2);
        thenResiduals("[0]");
    }

    @Test
    public void testCalculate_4_2_AnswerParts() {
        givenDataWhenCalculate(4, 2);
        thenAnswerParts("[2]");
    }

    @Test
    public void testCalculate_4_2_DotIndex() {
        givenDataWhenCalculate(4, 2);
        thenDotIndex(1);
    }

    @Test
    public void testCalculate_4_2_StartPeriodIndex() {
        givenDataWhenCalculate(4, 2);
        thenStartPeriodIndex(ABSENT);
    }

    @Test
    public void testCalculate_2930_2_45_Subtrahends() {
        givenDataWhenCalculate(2930, 2.45);
        thenSubtrahends("[245, 245, 2205, 1225, 2205, 245, 1960, 735, 1470, 1715, 735, 980, 1470, 2205, 735, 1960, " +
                "1715, 1715, 1225, 1225, 245, 0, 490, 0, 980, 0, 1960, 245, 1470, 735, 490, 1470, 1225, 735," +
                " 0, 1470, 245, 490, 490, 980, 980, 1960, 2205, 1715, 2205, 1225]");
    }

    @Test
    public void testCalculate_2930_2_45_Minuends() {
        givenDataWhenCalculate(2930, 2.45);
        thenMinuends("[293, 480, 2350, 1450, 2250, 450, 2050, 900, 1650, 1800, 850, 1150, 1700, 2300, 950, 2150, " +
                "1900, 1850, 1350, 1250, 250, 50, 500, 100, 1000, 200, 2000, 400, 1550, 800, 650, 1600, 1300, 750," +
                " 150, 1500, 300, 550, 600, 1100, 1200, 2200, 2400, 1950, 2350, 1450]");
    }

    @Test
    public void testCalculate_2930_2_45_Residuals() {
        givenDataWhenCalculate(2930, 2.45);
        thenResiduals("[48, 235, 145, 225, 45, 205, 90, 165, 180, 85, 115, 170, 230, 95, 215, 190, 185, 135, 125, " +
                "25, 5, 50, 10, 100, 20, 200, 40, 155, 80, 65, 160, 130, 75, 15, 150, 30, 55, 60, 110, 120, 220," +
                " 240, 195, 235, 145, 225]");
    }

    @Test
    public void testCalculate_2930_2_45_AnswerParts() {
        givenDataWhenCalculate(2930, 2.45);
        thenAnswerParts("[1, 1, 9, 5, 9, 1, 8, 3, 6, 7, 3, 4, 6, 9, 3, 8, 7, 7, 5, 5, 1, 0, 2, 0, 4, 0, 8, 1, 6, 3," +
                " 2, 6, 5, 3, 0, 6, 1, 2, 2, 4, 4, 8, 9, 7, 9, 5]");
    }

    @Test
    public void testCalculate_2930_2_45_DotIndex() {
        givenDataWhenCalculate(2930, 2.45);
        thenDotIndex(4);
    }

    @Test
    public void testCalculate_2930_2_45_StartPeriod() {
        givenDataWhenCalculate(2930, 2.45);
        thenStartPeriodIndex(4);
    }

    private void givenDataWhenCalculate(double numerator, double denominator) {
        givenData(numerator, denominator);
        whenCalculate();
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
    public void testHasIrrationalAnswer_2930_24() {
        testHasIrrationalAnswer(2930, 24, true);
    }

    @Test
    public void testHasIrrationalAnswer_190_4() {
        testHasIrrationalAnswer(190, 4, false);
    }

    @Test
    public void testHasIrrationalAnswer_2_40() {
        testHasIrrationalAnswer(2, 40, false);
    }

    @Test
    public void testHasIrrationalAnswer_4_2() {
        testHasIrrationalAnswer(4, 2, false);
    }

    @Test
    public void testHasIrrationalAnswer_2930_2_45() {
        testHasIrrationalAnswer(2930, 2.45, true);
    }

    private void testHasIrrationalAnswer(double numerator, double denominator, boolean expected) {
        givenDataWhenCalculate(numerator, denominator);
        thenHasIrrationalAnswer(expected);
    }

    private void thenHasIrrationalAnswer(boolean expected) {
        assertEquals(expected, data.hasIrrationalAnswer());
    }

    @Test
    public void testIsRationalSymptomDetected_2930_24() {
        testIsRationalSymptomDetected(2930, 24, false);
    }

    @Test
    public void testIsRationalSymptomDetected_190_4() {
        testIsRationalSymptomDetected(190, 4, true);
    }

    @Test
    public void testIsRationalSymptomDetected_2_40() {
        testIsRationalSymptomDetected(2, 40, true);
    }

    @Test
    public void testIsRationalSymptomDetected_4_2() {
        testIsRationalSymptomDetected(4, 2, true);
    }

    @Test
    public void testIsRationalSymptomDetected_2930_2_45() {
        testIsRationalSymptomDetected(2930, 2.45, false);
    }

    private void testIsRationalSymptomDetected(double numerator, double denominator, boolean expected) {
        givenDataWhenCalculate(numerator, denominator);
        thenIsRationalSymptomDetected(expected);
    }

    private void thenIsRationalSymptomDetected(boolean expected) {
        assertEquals(expected, data.isRationalSymptomDetected());
    }
}