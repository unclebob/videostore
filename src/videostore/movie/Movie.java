package videostore.movie;

public abstract class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getOwedAmount(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}