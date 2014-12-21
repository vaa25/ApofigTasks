package com.goit.apofig.launchers;

import com.goit.apofig.longDivision.LongDivision;

/**
 * http://ru.onlinemschool.com/math/assistance/number_theory/division/
 *
 * @author Alexander Vlasov
 */
public class LongDivisionLauncher {
    public static void main(String[] args) {
        LongDivision longDivision = new LongDivision();
        longDivision.calculate(2930, 2.45);
        System.out.println(longDivision.getLongAnswer());
        longDivision.calculate(4, 2);
        System.out.println(longDivision.getLongAnswer());
        longDivision.calculate(40, 2);
        System.out.println(longDivision.getLongAnswer());
        longDivision.calculate(2, 40);
        System.out.println(longDivision.getLongAnswer());
        longDivision.calculate(2930, 24);
        System.out.println(longDivision.getLongAnswer());

    }
}
