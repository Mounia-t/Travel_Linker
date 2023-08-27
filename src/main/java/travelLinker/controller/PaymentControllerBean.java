package travelLinker.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import travelLinker.dao.CartDao;
import travelLinker.dao.JourneyDao;
import travelLinker.dao.PaymentDao;
import travelLinker.entity.Account;
import travelLinker.entity.Journey;
import travelLinker.entity.Payment;
import travelLinker.entity.PaymentStatus;
import travelLinker.utils.SessionUtils;

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

	@Inject
	private SubscriptionController subscriptionController;
	@Inject
	private JourneyDao journeyDao;
	@Inject
	private Payment payment;

	private Long cartId;

	private String ownerName;
	private Journey selectedJourneyForPay;

  
	public void makePayment( ) {
		
		System.out.println("paymentDao: " + paymentDao);
		System.out.println("payment: " + payment);
		System.out.println("subscriptionController: " + subscriptionController);

		float totalAmount = 0.0f;
		payment.setAmount(totalAmount);
		payment.setPaymentDate(new Date());
		Account account=SessionUtils.getAccount();
		payment.setAccount(account); 
		 Long selectedJourneyId = getSelectedJourneyForPay().getId();
		    selectedJourneyForPay = journeyDao.findJourneyById(selectedJourneyId);
		    payment.setJourney(selectedJourneyForPay);
		System.out.println("ici les details " + payment.getAmount() + payment.getCardDate());

		if (processPayment(payment.getCardNumber(), payment.getCardDate(), totalAmount)) {
			payment.setPaymentStatus(PaymentStatus.PAID);
		} else {
			payment.setPaymentStatus(PaymentStatus.FAILED);
		}

		System.out.println("payment stat " + payment.getPaymentStatus());

		if (payment.getPaymentStatus() == PaymentStatus.PAID) {
			subscriptionController.createSubscriptionForTravelPlanner();
			paymentDao.createPayment(payment);
		}

		System.out.println("apres persistence: " + payment);
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
	  public Journey getSelectedJourneyForPay() {
	        return selectedJourneyForPay;
	    }

	    public void setSelectedJourneyForPay(Journey selectedJourneyForPay) {
	        this.selectedJourneyForPay = selectedJourneyForPay;
	    }

}
