package videostore;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import videostore.movie.ChildrensMovie;
import videostore.movie.Movie;
import videostore.movie.NewReleaseMovie;
import videostore.movie.RegularMovie;
import videostore.statement.Statement;
import videostore.statement.StatementBuilder;
import videostore.statement.StatementPrinter;

public class VideoStoreTest {
    private static final double DELTA = .001;
    private StatementBuilder statementBuilder;
    private Statement statement;
    private Movie newReleaseMovie1;
    private Movie newReleaseMovie2;
    private Movie childrensMovie;
    private Movie regularMovie1;
    private Movie regularMovie2;
    private Movie regularMovie3;

    @Before
    public void setUp() {
        statementBuilder = new StatementBuilder();
        statementBuilder.setCustomerName("Customer");
        newReleaseMovie1 = new NewReleaseMovie("New Release 1");
        newReleaseMovie2 = new NewReleaseMovie("New Release 2");
        childrensMovie = new ChildrensMovie("Children's");
        regularMovie1 = new RegularMovie("Regular 1");
        regularMovie2 = new RegularMovie("Regular 2");
        regularMovie3 = new RegularMovie("Regular 3");
    }

    private void givenRental(Movie movie, int daysRented) {
        statementBuilder.addRental(movie, daysRented);
    }

    private void whenBuildingStatement() {
        statement = statementBuilder.build();
    }

    private void shouldHaveRentalLines(double... owedAmounts) {
        assertEquals(owedAmounts.length, statement.getRentals().size());
        for (int i = 0; i < owedAmounts.length; i++)
            assertEquals(owedAmounts[i], statement.getRentals().get(i).getOwedAmount(), DELTA);
    }

    private void shouldOweATotalOf(double totalOwed) {
        assertEquals(totalOwed, statement.getTotalOwed(), DELTA);
    }

    private void shouldHaveFrequentRenterPoints(int frequentRenterPoints) {
        assertEquals(frequentRenterPoints, statement.getFrequentRenterPoints());
    }

    private void shouldPrint(String statementText) {
        assertEquals(statementText, new StatementPrinter(statement).print());
    }

    @Test
    public void testSingleNewReleaseStatement() {
        givenRental(newReleaseMovie1, 3);
        whenBuildingStatement();
        shouldHaveRentalLines(9.0);
        shouldOweATotalOf(9.0);
        shouldHaveFrequentRenterPoints(2);
    }

    @Test
    public void testSingleNewReleaseStatementOneDay() {
        givenRental(newReleaseMovie1, 1);
        whenBuildingStatement();
        shouldHaveRentalLines(3.0);
        shouldOweATotalOf(3.0);
        shouldHaveFrequentRenterPoints(1);
    }

    @Test
    public void testDualNewReleaseStatement() {
        givenRental(newReleaseMovie1, 3);
        givenRental(newReleaseMovie2, 3);
        whenBuildingStatement();
        shouldHaveRentalLines(9.0, 9.0);
        shouldOweATotalOf(18.0);
        shouldHaveFrequentRenterPoints(4);
    }

    @Test
    public void testSingleChildrensStatement() {
        givenRental(childrensMovie, 3);
        whenBuildingStatement();
        shouldHaveRentalLines(1.5);
        shouldOweATotalOf(1.5);
        shouldHaveFrequentRenterPoints(1);
    }

    @Test
    public void testSingleChildrensStatementOver3Days() {
        givenRental(childrensMovie, 4);
        whenBuildingStatement();
        shouldHaveRentalLines(3.0);
        shouldOweATotalOf(3.0);
        shouldHaveFrequentRenterPoints(1);
    }

    @Test
    public void testMultipleRegularStatement() {
        givenRental(regularMovie1, 1);
        givenRental(regularMovie2, 2);
        givenRental(regularMovie3, 3);
        whenBuildingStatement();
        shouldHaveRentalLines(2.0, 2.0, 3.5);
        shouldOweATotalOf(7.5);
        shouldHaveFrequentRenterPoints(3);
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        givenRental(regularMovie1, 1);
        givenRental(regularMovie2, 2);
        givenRental(regularMovie3, 3);
        whenBuildingStatement();
        shouldPrint("Rental Record for Customer\n" +
                "\tRegular 1\t2.0\n" +
                "\tRegular 2\t2.0\n" +
                "\tRegular 3\t3.5\n" +
                "You owed 7.5\n" +
                "You earned 3 frequent renter points\n");
    }
}