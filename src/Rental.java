
public class Rental
{
  private Movie movie;
  private int daysRented;

	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}

  public String getTitle() {
    return movie.getTitle();
  }

  public double determineAmount() {
    return movie.determineAmount(daysRented);
  }

  public int determineFrequentRenterPoints() {
    return movie.determineFrequentRenterPoints(daysRented);
  }
}