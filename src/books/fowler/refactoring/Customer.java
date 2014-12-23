package books.fowler.refactoring;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Alexander Vlasov
 */
class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Учет аренды для " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();

            //показать результаты для этой аренды
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }
        //добавить нижний колонтитул
        result += "Сумма задолженности составляет " +
                String.valueOf(getTotalCharge()) + "\n";
        result += "Вы заработали " + String.valueOf(getTotalFrequentRenterPoints()) +
                " очков за активность";
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            result += getFrequentRenterPoints(rentals.nextElement());
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            result += rentals.nextElement().getCharge();
        }
        return result;
    }
    private int getFrequentRenterPoints(Rental each) {
        int frequentRenterPoints = 1;
        // бонус за аренду новинки на два дня
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                each.getDaysRented() > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Операции аренды для <EM>" + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            // показать результаты по каждой аренде
            result += each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //добавить нижний колонтитул
        result += "<P>Ваша задолженность составляет <EM>" +
                String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "На этой аренде вы заработали <EM>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</EM> очков за активность<P>";
        return result;
    }

}