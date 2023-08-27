package travelLinker.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import travelLinker.viewModel.PaymentViewModel;

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
private JourneyDao journeyDao;
	@Inject
	private CartDao cartDao;
	private PaymentViewModel PVM= new PaymentViewModel();
	@Inject
	private SubscriptionController subscriptionController;

	
    private Long selectedJourneyId;

	private Long cartId;

	private String ownerName;
	private Journey selectedJourney;


  
	public void makePayment() {
		Payment payment =new Payment();
		float totalAmount = 0.0f;
		payment.setAmount(totalAmount);
		payment.setPaymentDate(new Date());
		Account account = SessionUtils.getAccount();
	    payment.setAccount(account);

	    if (selectedJourney != null) {
	        payment.setJourney(selectedJourney);		
	        System.out.println("ici les details " + payment.getAmount() + payment.getCardDate());
	    }
		if (processPayment(payment.getCardNumber(), payment.getCardDate(), totalAmount)) {
			payment.setPaymentStatus(PaymentStatus.PAID);
		} else {
			payment.setPaymentStatus(PaymentStatus.FAILED);
		}

		System.out.println("payment stat " + payment.getPaymentStatus());


			paymentDao.createPayment(payment);
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}


		System.out.println("apres persistence: " + payment);
	
	}
	public String reserve(Long journeyId) {
		// Utilisez journeyId pour charger le voyage sélectionné
		selectedJourney = journeyDao.findJourneyById(journeyId);
		paymentDao.makeReservationPayment();
		System.out.println("ma journey dans payment reserve " + selectedJourney);
		return "index.xhtml";

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

	  public Journey getSelectedJourney() {
	        return selectedJourney;
	    }

	    public void setSelectedJourneyForPay(Journey selectedJourney) {
	        this.selectedJourney = selectedJourney;
	    }

	    public Long getSelectedJourneyId() {
	        return selectedJourneyId;
	    }
	    

	    public PaymentDao getPaymentDao() {
			return paymentDao;
		}
		public void setPaymentDao(PaymentDao paymentDao) {
			this.paymentDao = paymentDao;
		}
		public JourneyDao getJourneyDao() {
			return journeyDao;
		}
		public void setJourneyDao(JourneyDao journeyDao) {
			this.journeyDao = journeyDao;
		}
		public PaymentViewModel getPVM() {
			return PVM;
		}
		public void setPVM(PaymentViewModel pVM) {
			PVM = pVM;
		}
		public void setSelectedJourney(Journey selectedJourney) {
			this.selectedJourney = selectedJourney;
		}
		public void setSelectedJourneyId(Long selectedJourneyId) {
	        this.selectedJourneyId = selectedJourneyId;
	    }

		public List<Payment> displayReservations(){
			return paymentDao.getReservations();
		}
		public int countTotalReservations() {
			return paymentDao.getTotalReservation();
		}
		
	}
	


	public Journey getSelectedJourneyForPay() {
		return selectedJourneyForPay;
	}

	public void setSelectedJourneyForPay(Journey selectedJourneyForPay) {
		this.selectedJourneyForPay = selectedJourneyForPay;
	}

}

