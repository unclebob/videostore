package videostore.movie;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    public double getOwedAmount(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}
