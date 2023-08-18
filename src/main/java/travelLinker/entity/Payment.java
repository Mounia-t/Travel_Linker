package travelLinker.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id_fk")
	private Customer customerId;

	@ManyToOne
	@JoinColumn(name = "partner_id_fk")
	private Partner partnerId;

	@ManyToOne
	@JoinColumn(name = "travelPlanner_id_fk")
	private TravelPlanner travelPlannerId;

	@ManyToOne
	@JoinColumn(name = "cart_id_fk")
	private Cart cart;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	private float amount;

	private Date paymentDate;

	private long cardNumber;

	private Date cardDate;

	private int numberCvv;

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCardDate() {
		return cardDate;
	}

	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}

	public int getNumberCvv() {
		return numberCvv;
	}

	public void setNumberCvv(int numberCvv) {
		this.numberCvv = numberCvv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Partner getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Partner partnerId) {
		this.partnerId = partnerId;
	}

	public TravelPlanner getTravelPlannerId() {
		return travelPlannerId;
	}

	public void setTravelPlannerId(TravelPlanner travelPlannerId) {
		this.travelPlannerId = travelPlannerId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
