package videostore.statement;

import videostore.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class StatementBuilder {
    private List<Rental> rentals = new ArrayList<Rental>();
    private String customerName;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Statement build() {
        return new Statement(customerName, rentals);
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }
}
