package travelLinker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Journey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String country;
	private int numberOfTravellers;
	private float price;
	private String location;
	private Date startDate;
	private Date endDate;


	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;



	private String imagePath;




	@ElementCollection
	@CollectionTable(name = "journey_restaurants", joinColumns = @JoinColumn(name = "journey_id"))
	@Column(name = "restaurant_list")
	private List<Restaurant> selectedRestaurants = new ArrayList<>();

	public Journey() {
	}

	public Journey(String country, int numberOfTravellers, float price, String location, Date startDate, Date endDate,
			byte[] imageFile) {

		super();
		this.country = country;
		this.numberOfTravellers = numberOfTravellers;
		this.price = price;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;

		this.description=Description;
		this.imagePath=imagePath;
		this.account=account;
		this.id=id;
	}
	



	public Long getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(int numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public List<Restaurant> getSelectedRestaurants() {
		return selectedRestaurants;
	}

	public void setSelectedRestaurants(List<Restaurant> selectedRestaurants) {
		this.selectedRestaurants = selectedRestaurants;
	}
}
