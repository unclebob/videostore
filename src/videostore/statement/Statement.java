package videostore.statement;

import java.util.List;

public class Statement {
    private String customerName;
    private List<Rental> rentals;
    private double totalOwed;
    private int frequentRenterPoints;

    public Statement(String customerName, List<Rental> rentals) {
        this.customerName = customerName;
        this.rentals = rentals;
        aggregate();
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public double getTotalOwed() {
        return totalOwed;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    private void aggregate() {
        for (Rental rental : rentals)
            aggregateRental(rental);
    }

    private void aggregateRental(Rental rental) {
        totalOwed += rental.getOwedAmount();
        frequentRenterPoints += rental.getFrequentRenterPoints();
    }
}