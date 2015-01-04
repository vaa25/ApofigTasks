package books.fowler.refactoring;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest extends TestCase {
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        //given
        customer = new Customer("Alexander");
        CustomerLauncher.setMovies();
        CustomerLauncher.rentMovies(customer);

    }
    @Test
    public void testStatement() throws Exception {
        //then
        assertEqualsArray(("Учет аренды для Alexander\n" +
                "\tLost\t2.0\n" +
                "\tElementary\t3.0\n" +
                "\tForever\t6.0\n" +
                "\tThe hundred\t9.0\n" +
                "\tOnce upon a time\t3.0\n" +
                "\tAscension\t15.0\n" +
                "\tGrimm\t6.0\n" +
                "\tBattleship Galaxy\t9.5\n" +
                "Сумма задолженности составляет 53.5\n" +
                        "Вы заработали 11 очков за активность").split("\\n"),
                customer.statement().split("\\n"));
    }

    @Test
    public void testHtmlStatement() throws Exception {
        //then
        assertNotNull(customer.htmlStatement());
        assertEqualsArray(("<H1>Операции аренды для <EM>Alexander</EM></H1><P>\n" +
                "Lost: 2.0<BR>\n" +
                "Elementary: 3.0<BR>\n" +
                "Forever: 6.0<BR>\n" +
                "The hundred: 9.0<BR>\n" +
                "Once upon a time: 3.0<BR>\n" +
                "Ascension: 15.0<BR>\n" +
                "Grimm: 6.0<BR>\n" +
                "Battleship Galaxy: 9.5<BR>\n" +
                "<P>Ваша задолженность составляет <EM>53.5</EM><P>\n" +
                        "На этой аренде вы заработали <EM>11</EM> очков за активность<P>").split("\\n"),
                customer.htmlStatement().split("\\n"));
    }

    private void assertEqualsArray(String[] example, String[] result) {
        assertEqualsArrayLength(example, result);
        assertEqualsArrayBody(example, result);
    }

    private void assertEqualsArrayLength(String[] example, String[] result) {
        assertEquals(example.length, result.length);
    }

    private void assertEqualsArrayBody(String[] example, String[] result) {
        for (int i = 0; i < example.length; i++) {
            assertEquals(example[i], result[i]);
        }
    }
}