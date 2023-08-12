package travelLinker.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "cart")
	private List<Item> items;

	@OneToOne
	@JoinColumn(name = "customer_id_fk")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "travel_planer_id_fk")
	private TravelPlanner travelPlanner;

	@OneToOne
	@JoinColumn(name = "partner_id_fk")
	private Partner partner;

}
