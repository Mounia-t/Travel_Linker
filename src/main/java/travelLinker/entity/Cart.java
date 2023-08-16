package travelLinker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "customer_id_fk")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "travel_planer_id_fk")
	private TravelPlanner travelPlanner;

	@OneToOne
	@JoinColumn(name = "partner_id_fk")
	private Partner partner;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TravelPlanner getTravelPlanner() {
		return travelPlanner;
	}

	public void setTravelPlanner(TravelPlanner travelPlanner) {
		this.travelPlanner = travelPlanner;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Long getId() {
		return id;
	}

}
