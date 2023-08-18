package travelLinker.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import travelLinker.dao.CartDao;
import travelLinker.dao.PaymentDao;
import travelLinker.entity.Cart;
import travelLinker.entity.Item;
import travelLinker.entity.Payment;
import travelLinker.entity.PaymentStatus;

public class PaymentControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PaymentDao paymentDao;

	@Inject
	private CartDao cartDao;

	public void makePayment(Long cartId, long cardNumber, Date cardDate, int numberCvv) {
		try {
			if (!validateCardDetails(cardNumber, cardDate, numberCvv)) {
				return;
			}

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
				payment.setCardNumber(cardNumber);
				payment.setCardDate(cardDate);
				payment.setNumberCvv(numberCvv);

				if (processPayment(cardNumber, cardDate, totalAmount)) {
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

	private boolean validateCardDetails(long cardNumber, Date cardDate, int numberCvv) {
		String cardNumberStr = Long.toString(cardNumber);
		if (cardNumberStr.length() != 16) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();

		if (cardDate.before(currentDate)) {
			return false;
		}
		String cvvStr = Integer.toString(numberCvv);
		if (cvvStr.length() < 3 || cvvStr.length() > 4) {
			return false; //
		}

		return true;
	}

	private boolean processPayment(long cardNumber, Date cardDate, float totalAmount) {
		double randomValue = Math.random();

		if (randomValue < 0.9) {
			return true;
		} else {
			return false;
		}
	}

}
