package travelLinker.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantity;
	private Date startDate;
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "journey_id_fk")
	private Journey journey;

	@ManyToOne
	@JoinColumn(name = "accomodation_id_fk")
	private Accomodation accomodation;

	@ManyToOne
	@JoinColumn(name = "transport_id_fk")
	private Transport transport;

	@ManyToOne
	@JoinColumn(name = "restaurant_id_fk")
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "cart_id_fk")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "subscription_id_fk")
	private Subscription subscription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
