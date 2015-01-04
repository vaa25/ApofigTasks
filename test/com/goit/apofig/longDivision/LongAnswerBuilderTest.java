package com.goit.apofig.longDivision;

import junit.framework.TestCase;
import org.junit.Test;


public class LongAnswerBuilderTest extends TestCase {
    private Data data;
    private LongAnswerBuilder builder;

    @Test
    public void testBuildLongAnswer() throws Exception {
        given(2930, 2.45);
        thenBulidLongAnswer(" 293000|245\n" +
                        "-      +-------------------------------------------------\n" +
                        " 245   |1195.(918367346938775510204081632653061224489795)\n" +
                        " ---\n" +
                        "  480\n" +
                        " -\n" +
                        "  245\n" +
                        "  ---\n" +
                        "  2350\n" +
                        " -\n" +
                        "  2205\n" +
                        "  ----\n" +
                        "   1450\n" +
                        "  -\n" +
                        "   1225\n" +
                        "   ----\n" +
                        "    2250\n" +
                        "   -\n" +
                        "    2205\n" +
                        "    ----\n" +
                        "      450\n" +
                        "     -\n" +
                        "      245\n" +
                        "      ---\n" +
                        "      2050\n" +
                        "     -\n" +
                        "      1960\n" +
                        "      ----\n" +
                        "        900\n" +
                        "       -\n" +
                        "        735\n" +
                        "        ---\n" +
                        "        1650\n" +
                        "       -\n" +
                        "        1470\n" +
                        "        ----\n" +
                        "         1800\n" +
                        "        -\n" +
                        "         1715\n" +
                        "         ----\n" +
                        "           850\n" +
                        "          -\n" +
                        "           735\n" +
                        "           ---\n" +
                        "           1150\n" +
                        "          -\n" +
                        "            980\n" +
                        "            ---\n" +
                        "            1700\n" +
                        "           -\n" +
                        "            1470\n" +
                        "            ----\n" +
                        "             2300\n" +
                        "            -\n" +
                        "             2205\n" +
                        "             ----\n" +
                        "               950\n" +
                        "              -\n" +
                        "               735\n" +
                        "               ---\n" +
                        "               2150\n" +
                        "              -\n" +
                        "               1960\n" +
                        "               ----\n" +
                        "                1900\n" +
                        "               -\n" +
                        "                1715\n" +
                        "                ----\n" +
                        "                 1850\n" +
                        "                -\n" +
                        "                 1715\n" +
                        "                 ----\n" +
                        "                  1350\n" +
                        "                 -\n" +
                        "                  1225\n" +
                        "                  ----\n" +
                        "                   1250\n" +
                        "                  -\n" +
                        "                   1225\n" +
                        "                   ----\n" +
                        "                     250\n" +
                        "                    -\n" +
                        "                     245\n" +
                        "                     ---\n" +
                        "                       500\n" +
                        "                      -\n" +
                        "                       490\n" +
                        "                       ---\n" +
                        "                        1000\n" +
                        "                       -\n" +
                        "                         980\n" +
                        "                         ---\n" +
                        "                          2000\n" +
                        "                         -\n" +
                        "                          1960\n" +
                        "                          ----\n" +
                        "                            400\n" +
                        "                           -\n" +
                        "                            245\n" +
                        "                            ---\n" +
                        "                            1550\n" +
                        "                           -\n" +
                        "                            1470\n" +
                        "                            ----\n" +
                        "                              800\n" +
                        "                             -\n" +
                        "                              735\n" +
                        "                              ---\n" +
                        "                               650\n" +
                        "                              -\n" +
                        "                               490\n" +
                        "                               ---\n" +
                        "                               1600\n" +
                        "                              -\n" +
                        "                               1470\n" +
                        "                               ----\n" +
                        "                                1300\n" +
                        "                               -\n" +
                        "                                1225\n" +
                        "                                ----\n" +
                        "                                  750\n" +
                        "                                 -\n" +
                        "                                  735\n" +
                        "                                  ---\n" +
                        "                                   1500\n" +
                        "                                  -\n" +
                        "                                   1470\n" +
                        "                                   ----\n" +
                        "                                     300\n" +
                        "                                    -\n" +
                        "                                     245\n" +
                        "                                     ---\n" +
                        "                                      550\n" +
                        "                                     -\n" +
                        "                                      490\n" +
                        "                                      ---\n" +
                        "                                       600\n" +
                        "                                      -\n" +
                        "                                       490\n" +
                        "                                       ---\n" +
                        "                                       1100\n" +
                        "                                      -\n" +
                        "                                        980\n" +
                        "                                        ---\n" +
                        "                                        1200\n" +
                        "                                       -\n" +
                        "                                         980\n" +
                        "                                         ---\n" +
                        "                                         2200\n" +
                        "                                        -\n" +
                        "                                         1960\n" +
                        "                                         ----\n" +
                        "                                          2400\n" +
                        "                                         -\n" +
                        "                                          2205\n" +
                        "                                          ----\n" +
                        "                                           1950\n" +
                        "                                          -\n" +
                        "                                           1715\n" +
                        "                                           ----\n" +
                        "                                            2350\n" +
                        "                                           -\n" +
                        "                                            2205\n" +
                        "                                            ----\n" +
                        "                                             1450\n" +
                        "                                            -\n" +
                        "                                             1225\n" +
                        "                                             ----\n" +
                        "                                              225");

        given(4, 2);
        thenBulidLongAnswer(" 4|2\n" +
                        "- +-\n" +
                        " 4|2\n" +
                        " -\n" +
                        "  0");

        given(40, 2);
        thenBulidLongAnswer(" 40|2\n" +
                        "-  +--\n" +
                        " 4 |20\n" +
                        " -\n" +
                        "  0");

        given(2, 40);
        thenBulidLongAnswer(" 2|40\n" +
                        "- +----\n" +
                        " 0|0.05\n" +
                        " -\n" +
                        "  200\n" +
                        " -\n" +
                        "  200\n" +
                        "  ---\n" +
                        "    0");

        given(2930, 24);
        thenBulidLongAnswer(" 2930|24\n" +
                        "-    +---------\n" +
                        " 24  |122.08(3)\n" +
                        " --\n" +
                        "  53\n" +
                        " -\n" +
                        "  48\n" +
                        "  --\n" +
                        "   50\n" +
                        "  -\n" +
                        "   48\n" +
                        "   --\n" +
                        "    200\n" +
                        "   -\n" +
                        "    192\n" +
                        "    ---\n" +
                        "      80\n" +
                        "     -\n" +
                        "      72\n" +
                        "      --\n" +
                        "       8");

        given(190, 4);
        thenBulidLongAnswer(" 190|4\n" +
                        "-   +----\n" +
                        " 16 |47.5\n" +
                        " --\n" +
                        "  30\n" +
                        " -\n" +
                        "  28\n" +
                        "  --\n" +
                        "   20\n" +
                        "  -\n" +
                        "   20\n" +
                        "   --\n" +
                        "    0");
    }

    private void thenBulidLongAnswer(String expected) {
        assertEquals(expected, builder.buildLongAnswer());
    }

    private void given(double numerator, double denominator) {
        data = new Data(numerator, denominator);
        data.calculate();
        builder = new LongAnswerBuilder(data);
    }


}