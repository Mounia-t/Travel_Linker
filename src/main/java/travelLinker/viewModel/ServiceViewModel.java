package travelLinker.viewModel;

import java.util.Date;

public class ServiceViewModel {

	private float price;
	private String type;
	private String country;
	private String location;
	private Date startDate;
	private Date endDate;
	private String name;
	private String description;
	private String typeOfAccomodation;
	private String typeOfRestaurant;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getTypeOfAccomodation() {
		return typeOfAccomodation;
	}

	public void setTypeOfAccomodation(String typeOfAccomodation) {
		this.typeOfAccomodation = typeOfAccomodation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccomodationViewModel [price=");
		builder.append(price);
		builder.append(", type=");
		builder.append(type);
		builder.append(", country=");
		builder.append(country);
		builder.append(", location=");
		builder.append(location);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", typeOfAccomodation=");
		builder.append(typeOfAccomodation);
		builder.append("]");
		return builder.toString();
	}

	public String getTypeOfRestaurant() {
		return typeOfRestaurant;
	}

	public void setTypeOfRestaurant(String typeOfRestaurant) {
		this.typeOfRestaurant = typeOfRestaurant;
	}

}
