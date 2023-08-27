package travelLinker.viewModel;

import java.util.Date;

import travelLinker.entity.PaymentStatus;

public class PaymentViewModel {

	private Long id;

	private Long customerId;

	private Long partnerId;

	private Long travelPlannerId;

	private Long cartId;

	private PaymentStatus paymentStatus;

	private String cardType;
	private String cardDate;

	private float amount;

	private int numberCvv;
	private Date paymentDate;
	private long cardNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getTravelPlannerId() {
		return travelPlannerId;
	}

	public void setTravelPlannerId(Long travelPlannerId) {
		this.travelPlannerId = travelPlannerId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	

}
