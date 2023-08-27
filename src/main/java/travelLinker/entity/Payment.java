package travelLinker.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private TravelPlanner travelPlanner;

	@ManyToOne
	@JoinColumn(name = "cart_id_fk")
	private Cart cart;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_id_fk")
	private Subscription subscription;

	public Subscription getSubscription() {
		return subscription;
	}

	@ManyToOne
	@JoinColumn(name = "account_id_fk")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	private float amount;

	private Date paymentDate;

	private long cardNumber;

	private String cardDate;

	private int numberCvv;

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardDate() {
		return cardDate;
	}

	public void setCardDate(String cardDate) {
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

	public TravelPlanner getTravelPlanner() {
		return travelPlanner;
	}

	public void setTravelPlanner(TravelPlanner travelPlanner) {
		this.travelPlanner = travelPlanner;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=");
		builder.append(id);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", partnerId=");
		builder.append(partnerId);
		builder.append(", travelPlannerId=");
		builder.append(travelPlanner);
		builder.append(", cart=");
		builder.append(cart);
		builder.append(", paymentStatus=");
		builder.append(paymentStatus);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", paymentDate=");
		builder.append(paymentDate);
		builder.append(", cardNumber=");
		builder.append(cardNumber);
		builder.append(", cardDate=");
		builder.append(cardDate);
		builder.append(", numberCvv=");
		builder.append(numberCvv);
		builder.append("]");
		return builder.toString();
	}

}
