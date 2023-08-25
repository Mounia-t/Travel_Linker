package travelLinker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Restaurant extends Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "journey_id_fk")
	private Journey journey;

	private String typeOfRestaurant;

	public Long getId() {
		return id;
	}

	public String getTypeOfRestaurant() {
		return typeOfRestaurant;
	}

	public void setTypeOfRestaurant(String typeOfRestaurant) {
		this.typeOfRestaurant = typeOfRestaurant;
	}

}
