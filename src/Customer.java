// This file is the original program.  It has been left here so you can compare it with the refactored version.
import java.util.Vector;
import java.util.Enumeration;

public class Customer 
{
	public Customer (String name) {
		this.name = name;
	}
	
	public void addRental (Rental rental) {
		rentals.addElement (rental);
	}
	
	public String getName () {
		return name;
	}
	
	public String statement () {
		double 				totalAmount 			= 0;
		int					frequentRenterPoints 	= 0;
		Enumeration 		rentals 				= this.rentals.elements ();
		String 				result 					= "Rental Record for " + getName () + "\n";
		
		while (rentals.hasMoreElements ()) {
			double 		thisAmount = 0;
			Rental 		each = (Rental)rentals.nextElement ();
			
			// determines the amount for each line
			switch (each.getMovie ().getPriceCode ()) {
				case Movie.REGULAR:
					thisAmount += 2;
					if (each.getDaysRented () > 2)
						thisAmount += (each.getDaysRented () - 2) * 1.5;
					break;
				case Movie.NEW_RELEASE:
					thisAmount += each.getDaysRented () * 3;
					break;
				case Movie.CHILDRENS:
					thisAmount += 1.5;
					if (each.getDaysRented () > 3)
						thisAmount += (each.getDaysRented () - 3) * 1.5;
					break;
			}
			
			frequentRenterPoints++;
			
			if (each.getMovie ().getPriceCode () == Movie.NEW_RELEASE 
					&& each.getDaysRented () > 1)
				frequentRenterPoints++;
				
			result += "\t" + each.getMovie ().getTitle () + "\t"
								+ String.valueOf (thisAmount) + "\n";
			totalAmount += thisAmount;
				
		}
		
		result += "You owed " + String.valueOf (totalAmount) + "\n";
		result += "You earned " + String.valueOf (frequentRenterPoints) + " frequent renter points\n";
		
		
		return result;
	}
	

	private String name;
	private Vector rentals = new Vector ();
}
