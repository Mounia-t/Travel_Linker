package travelLinker.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.CartDao;
import travelLinker.dao.PaymentDao;
import travelLinker.entity.Cart;
import travelLinker.entity.Item;
import travelLinker.entity.Payment;
import travelLinker.entity.PaymentStatus;

@ManagedBean
@SessionScoped
public class PaymentControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PaymentDao paymentDao;

	@Inject
	private CartDao cartDao;

	private Payment payment = new Payment();

	private Long cartId;

	private String ownerName;

	public void makePayment() {
		System.out.println("make payment " + payment);
		try {
			if (!validateCardDetails(payment.getCardNumber(), payment.getCardDate(), payment.getNumberCvv())) {
				return;
			}
			System.out.println("make Payment " + payment.getCardNumber() + payment.getCardDate());

			Cart cart = cartDao.findByCartId(cartId);
			if (cart != null) {
				float totalAmount = 0.0f;

				for (Item item : cart.getItems()) {
					totalAmount += item.getPrice() * item.getQuantity();
				}

				Payment payment = new Payment();
				payment.setCart(cart);
				payment.setAmount(totalAmount);
				payment.setPaymentDate(new Date());

				if (processPayment(payment.getCardNumber(), payment.getCardDate(), totalAmount)) {
					payment.setPaymentStatus(PaymentStatus.PAID);
				} else {
					payment.setPaymentStatus(PaymentStatus.FAILED);
				}

				paymentDao.createPayment(payment);
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	private boolean validateCardDetails(long cardNumber, String cardDate, int numberCvv) {
		String cardNumberStr = Long.toString(cardNumber);
		if (cardNumberStr.length() != 16) {
			return false;
		}

		SimpleDateFormat format = new SimpleDateFormat("MMyy");
		Date dateFromCard;
		try {
			dateFromCard = format.parse(cardDate);
		} catch (ParseException e) {
			return false;
		}

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();

		if (dateFromCard.before(currentDate)) {
			return false;
		}

		String cvvStr = Integer.toString(numberCvv);
		if (cvvStr.length() < 3 || cvvStr.length() > 4) {
			return false;
		}
		return true;
	}

	private boolean processPayment(long cardNumber, String cardDate, float totalAmount) {
		double randomValue = Math.random();

		if (randomValue < 1) {
			return true;
		} else {
			return false;
		}
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}