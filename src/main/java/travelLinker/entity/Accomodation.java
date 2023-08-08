package travelLinker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accomodation extends Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String typeOfAccomodation;

	public Long getId() {
		return id;
	}

	public String getTypeOfAccomodation() {
		return typeOfAccomodation;
	}

	public void setTypeOfAccomodation(String typeOfAccomodation) {
		this.typeOfAccomodation = typeOfAccomodation;
	}

}