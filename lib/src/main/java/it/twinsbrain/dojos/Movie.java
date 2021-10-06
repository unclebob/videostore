package it.twinsbrain.dojos;

public abstract class Movie {
    private final String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double determineAmount(int daysRented);

    public abstract int determineFrequentRenterPoints(int daysRented);
}