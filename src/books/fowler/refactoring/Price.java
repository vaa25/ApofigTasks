package books.fowler.refactoring;

/**
 * @author Alexander Vlasov
 */
abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}

class ChildrensPrice extends Price {
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    double getCharge(int daysRented) {
        return (daysRented > 3) ? (1.5 + (daysRented - 3) * 1.5) : 1.5;
    }


}

class NewReleasePrice extends Price {
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    int getFrequentRenterPoints(int daysRented) {

        // бонус за аренду новинки на два дня
        return (daysRented > 1) ? 2 : 1;
    }
}

class RegularPrice extends Price {
    int getPriceCode() {
        return Movie.REGULAR;
    }

    double getCharge(int daysRented) {
        return (daysRented > 2) ? (2 + (daysRented - 2) * 1.5) : 2;
    }
}
