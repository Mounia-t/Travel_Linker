package travelLinker.viewModel;


	import java.util.Date;


	
import java.util.Date;


public class JourneyViewModel {
	
	private String country;
	private int numberOfTravellers;
	private float price;
	private String location;
	private Date startDate;
	private Date endDate;

	
	public String getCountry() {
		return country;
	}
	public int getNumberOfTravellers() {
		return numberOfTravellers;
	}
	public float getPrice() {
		return price;
	}
	public String getLocation() {
		return location;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setNumberOfTravellers(int numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}

