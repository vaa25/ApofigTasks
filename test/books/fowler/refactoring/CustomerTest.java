package books.fowler.refactoring;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Alexander");
        CustomerLauncher.setMovies();
        CustomerLauncher.rentMovies(customer);

    }
    @Test
    public void testStatement() throws Exception {
        String example = "Учет аренды для Alexander\n" +
                "\tLost\t2.0\n" +
                "\tElementary\t3.0\n" +
                "\tForever\t6.0\n" +
                "\tThe hundred\t9.0\n" +
                "\tOnce upon a time\t3.0\n" +
                "\tAscension\t15.0\n" +
                "\tGrimm\t6.0\n" +
                "\tBattleship Galaxy\t9.5\n" +
                "Сумма задолженности составляет 53.5\n" +
                "Вы заработали 11 очков за активность";
        String result = customer.statement();
        if (!example.equals(result)) {
            System.out.println("Failed!!!!!! \n");
            System.out.println("Example: \n");
            System.out.println(example);
            System.out.println();
            System.out.println("Reality: \n");
            System.out.println(result);
        } else {
            System.out.println("Well done!!!");
        }
    }

    @Test
    public void testHtmlStatement() throws Exception {
        String example = "<H1>Операции аренды для <EM>Alexander</EM></H1><P>\n" +
                "Lost: 2.0<BR>\n" +
                "Elementary: 3.0<BR>\n" +
                "Forever: 6.0<BR>\n" +
                "The hundred: 9.0<BR>\n" +
                "Once upon a time: 3.0<BR>\n" +
                "Ascension: 15.0<BR>\n" +
                "Grimm: 6.0<BR>\n" +
                "Battleship Galaxy: 9.5<BR>\n" +
                "<P>Ваша задолженность составляет <EM>53.5</EM><P>\n" +
                "На этой аренде вы заработали <EM>11</EM> очков за активность<P>";
        String result = customer.htmlStatement();
        if (!example.equals(result)) {
            System.out.println("Failed!!!!!! \n");
            System.out.println("Example: \n");
            System.out.println(example);
            System.out.println();
            System.out.println("Reality: \n");
            System.out.println(result);
        } else {
            System.out.println("Well done!!!");
        }
    }
}