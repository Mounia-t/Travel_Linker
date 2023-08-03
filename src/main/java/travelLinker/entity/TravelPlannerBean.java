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
	private String lastName; 
	private String firstName; 
	private String address; 
	private String email;
	private String password;
	private double phoneNumber;
	private String siret;
	private String companyName;
	//private AccountBean accountBean;
	
	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
