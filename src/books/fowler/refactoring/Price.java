package books.fowler.refactoring;

/**
 * @author Alexander Vlasov
 */
abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);
}

class ChildrensPrice extends Price {
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    double getCharge(int daysRented) {
        if (daysRented > 3) {
            return 1.5 + (daysRented - 3) * 1.5;
        } else {
            return 1.5;
        }
    }
}

class NewReleasePrice extends Price {
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }
}

class RegularPrice extends Price {
    int getPriceCode() {
        return Movie.REGULAR;
    }

    double getCharge(int daysRented) {
        if (daysRented > 2)
            return 2 + (daysRented - 2) * 1.5;
        else {
            return 2;
        }
    }
}
