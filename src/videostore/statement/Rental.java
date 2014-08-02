package videostore.statement;

import videostore.movie.Movie;

public class Rental {
    private Movie movie;
    private int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }

    public double getOwedAmount() {
        return movie.getOwedAmount(daysRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }
}