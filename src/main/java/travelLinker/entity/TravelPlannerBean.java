package travelLinker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TravelPlannerBean{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	private String travelPlannerName; 
	private String address; 
	private String email;
	private double phoneNumber;
	private String siret;
	private String companyName;
	//private AccountBean accountBean;
	
	public Long getId() {
		return id;
	}
	
	public String getTravelPlannerName() {
		return travelPlannerName;
	}
	public void setTravelPlannerName(String travelPlannerName) {
		this.travelPlannerName = travelPlannerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
	
}
