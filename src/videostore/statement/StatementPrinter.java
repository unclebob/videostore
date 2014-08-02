package videostore.statement;

public class StatementPrinter {
    private Statement statement;

    public StatementPrinter(Statement statement) {
        this.statement = statement;
    }

    public String print() {
        return printCustomer() + printRentals() + printTotalOwed() + printFrequentRenterPoints();
    }

    private String printCustomer() {
        return String.format("Rental Record for %s\n", statement.getCustomerName());
    }

    private String printRentals() {
        String rentals = "";
        for (Rental rental : statement.getRentals()) rentals += printRental(rental);
        return rentals;
    }

    private String printRental(Rental rental) {
        return String.format("\t%s\t%.1f\n", rental.getMovieTitle(), rental.getOwedAmount());
    }

    private String printTotalOwed() {
        return String.format("You owed %.1f\n", statement.getTotalOwed());
    }

    private String printFrequentRenterPoints() {
        return String.format("You earned %d frequent renter points\n", statement.getFrequentRenterPoints());
    }
}