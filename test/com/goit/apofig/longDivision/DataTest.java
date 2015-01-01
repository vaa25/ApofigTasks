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
        Data data = new Data(2930, 2.45);
        data.calculate();
    }

    @Test
    public void testHasIrrationalAnswer() throws Exception {

    }

    @Test
    public void testIsRationalSymptomDetected() throws Exception {

    }
}