package books.fowler.refactoring;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Vlasov
 */
public class CustomerLauncher {
    private static List<Movie> movies;

    public static void main(String[] args) {
        setMovies();
        Customer customer = new Customer("Alexander");
        rentMovies(customer);

        System.out.println(customer.statement());
    }

    public static void rentMovies(Customer customer) {
        for (int i = 0; i < movies.size(); i++) {
            customer.addRental(new Rental(movies.get(i), i));

        }
    }

    public static void setMovies() {
        movies = new ArrayList<>();
        movies.add(new Movie("Lost", 0));
        movies.add(new Movie("Elementary", 1));
        movies.add(new Movie("Forever", 1));
        movies.add(new Movie("The hundred", 1));
        movies.add(new Movie("Once upon a time", 2));
        movies.add(new Movie("Ascension", 1));
        movies.add(new Movie("Grimm", 2));
        movies.add(new Movie("Battleship Galaxy", 0));
    }

}
