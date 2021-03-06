package com.goit.apofig.launchers;

import com.goit.apofig.longDivision.LongDivision;

/**
 * http://ru.onlinemschool.com/math/assistance/number_theory/division/
 *
 * @author Alexander Vlasov
 */
public class LongDivisionLauncher {
    public static void main(String[] args) {
        run(2930, 2.45);
        run(4, 2);
        run(40, 2);
        run(2, 40);
        run(2930, 24);
        run(190, 4);

    }

    static void run(double numerator, double denominator) {
        LongDivision longDivision = new LongDivision();
        longDivision.calculate(numerator, denominator);
        System.out.println(longDivision.getLongAnswer());
    }
}
