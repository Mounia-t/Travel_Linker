package travelLinker.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String type ;
	private String description;
	private float price;
	private String country;
	private String location;
	private Date startDate;
	private Date endDate;

	public Insurance (){
			}

	public Insurance(String type, float price, String description, Date startDate,
			Date endDate) {
		super();
		this.type = type;
		this.description = description;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

	public String getCountry() {
		return country;
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


	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setCountry(String country) {
		this.country = country;
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