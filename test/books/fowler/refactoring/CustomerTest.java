package books.fowler.refactoring;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void testStatement() throws Exception {
        Customer customer = new Customer("Alexander");
        CustomerLauncher.setMovies();
        CustomerLauncher.rentMovies(customer);
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
        if (!example.equals(customer.statement())) {
            System.out.println("Example: \n");
            System.out.println(example);
            System.out.println();
            System.out.println("Reality: \n");
        }
    }
}