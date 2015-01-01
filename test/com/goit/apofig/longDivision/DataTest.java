package com.goit.apofig.longDivision;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * run(2930, 2.45);
 * run(4, 2);
 * run(40, 2);
 * run(2, 40);
 * run(2930, 24);
 * run(190, 4);
 */
public class DataTest extends TestCase {
    private final int ABSENT = Data.ABSENT;
    @Test
    public void testData() throws Exception {
        Numerator numerator = new Numerator(2930);
        SourceValue denominator = new SourceValue(2.45);
        SourceValue.cast(numerator, denominator);
        Data data = new Data(2930, 2.45);
        assertEquals(numerator, data.getNumerator());
        assertEquals(denominator, data.getDenominator());
    }

    @Test
    public void testCalculate() throws Exception {
        testValues(2930, 2.45, 4, 4,
                "[1, 1, 9, 5, 9, 1, 8, 3, 6, 7, 3, 4, 6, 9, 3, 8, 7, 7, 5, 5, 1, 0, 2, 0, 4, 0, 8, 1, 6, 3, 2, " +
                        "6, 5, 3, 0, 6, 1, 2, 2, 4, 4, 8, 9, 7, 9, 5]",
                "[48, 235, 145, 225, 45, 205, 90, 165, 180, 85, 115, 170, 230, 95, 215, 190, 185, 135, 125, 25, " +
                        "5, 50, 10, 100, 20, 200, 40, 155, 80, 65, 160, 130, 75, 15, 150, 30, 55, 60, 110, 120, 220," +
                        " 240, 195, 235, 145, 225]",
                "[480, 2350, 1450, 2250, 450, 2050, 900, 1650, 1800, 850, 1150, 1700, 2300, 950, 2150, 1900, " +
                        "1850, 1350, 1250, 250, 50, 500, 100, 1000, 200, 2000, 400, 1550, 800, 650, 1600, 1300, 750," +
                        " 150, 1500, 300, 550, 600, 1100, 1200, 2200, 2400, 1950, 2350, 1450, 225]",
                "[245, 245, 2205, 1225, 2205, 245, 1960, 735, 1470, 1715, 735, 980, 1470, 2205, 735, 1960, " +
                        "1715, 1715, 1225, 1225, 245, 0, 490, 0, 980, 0, 1960, 245, 1470, 735, 490, 1470, 1225, 735," +
                        " 0, 1470, 245, 490, 490, 980, 980, 1960, 2205, 1715, 2205, 1225]");
        testValues(4, 2, ABSENT, 1, "[2]", "[0]", "[0]", "[4]");
        testValues(40, 2, ABSENT, 2, "[2, 0]", "[0, 0]", "[0, 0]", "[4, 0]");
        testValues(2, 40, ABSENT, 1, "[0, 0, 5]", "[2, 20, 0]", "[20, 200, 0]", "[0, 0, 200]");
        testValues(190, 4, ABSENT, 2, "[4, 7, 5]", "[3, 2, 0]", "[30, 20, 0]", "[16, 28, 20]");
        testValues(2930, 24, 5, 3, "[1, 2, 2, 0, 8, 3]", "[5, 5, 2, 20, 8, 8]", "[53, 50, 20, 200, 80, 8]",
                "[24, 48, 48, 0, 192, 72]");
    }


    private void testValues(double numerator, double denominator, int startPeriodIndex, int dotIndex,
                            String answerParts, String residuals, String minuends, String subtrahends) {
        Data data = new Data(numerator, denominator);
        data.calculate();
        assertEquals(startPeriodIndex, data.getStartPeriodIndex());
        assertEquals(dotIndex, data.getDotIndex());
        assertEquals(answerParts, data.getAnswerParts().toString());
        assertEquals(residuals, data.getResiduals().toString());
//        assertEquals(minuends, data.getMinuends().toString());
        assertEquals(subtrahends, data.getSubtrahends().toString());
    }

    @Test
    public void testHasIrrationalAnswer() throws Exception {
        testHia(2930, 2.45, true);
        testHia(4, 2, false);
        testHia(2, 40, false);
        testHia(190, 4, false);
        testHia(2930, 24, true);
    }

    private void testHia(double numerator, double denominator, boolean expected) {
        Data data = new Data(numerator, denominator);
        data.calculate();
        assertEquals(expected, data.hasIrrationalAnswer());
    }
    @Test
    public void testIsRationalSymptomDetected() throws Exception {
        testIrsd(2930, 2.45, false);
        testIrsd(4, 2, true);
        testIrsd(2, 40, true);
        testIrsd(190, 4, true);
        testIrsd(2930, 24, false);
    }

    private void testIrsd(double numerator, double denominator, boolean expected) {
        Data data = new Data(numerator, denominator);
        data.calculate();
        assertEquals(expected, data.isRationalSymptomDetected());
    }
}