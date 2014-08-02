package videostore.movie;

public class ChildrensMovie extends Movie {
    public ChildrensMovie(String title) {
        super(title);
    }

    public double getOwedAmount(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
