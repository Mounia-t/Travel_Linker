package travelLinker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import travelLinker.entity.Account;
import travelLinker.entity.Journey;
import travelLinker.entity.Payment;
import travelLinker.entity.PaymentStatus;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.PaymentViewModel;




@Stateless
public class PaymentDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private JourneyDao journeyDao;
	private Journey selectedJourney;
	private PaymentViewModel PVM = new PaymentViewModel();


	public Long createPayment(Payment payment) {
		try {
			entityManager.persist(payment);
			entityManager.flush(); // Ceci force la synchronisation avec la base de données
			return payment.getId();
		} catch (Exception e) {
			e.printStackTrace(); // Affiche l'erreur pour un diagnostic
			throw e; // Relance l'erreur pour informer l'appelant qu'une erreur s'est produite
		}

	}

	public void makeReservationPayment() {
		Payment payment = new Payment();
		float totalAmount = 0.0f;
		payment.setAmount(totalAmount);
		payment.setPaymentDate(new Date());
		Account account = SessionUtils.getAccount();
		payment.setAccount(account);
		payment.setCardNumber(PVM.getCardNumber());
		payment.setCardDate(PVM.getCardDate());
		payment.setNumberCvv(PVM.getNumberCvv());
		if (selectedJourney != null) {
			payment.setJourney(selectedJourney);

			entityManager.persist(payment);
			entityManager.flush();
		}
	}


	public Payment findPaymentById(Long paymentId) {
		return entityManager.find(Payment.class, paymentId);
	}

	public Payment findByCartId(Long cartId) {
		try {
			return entityManager.createQuery("SELECT p FROM Payment p WHERE p.cart.id = :cartId", Payment.class)
					.setParameter("cartId", cartId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void updatePayment(Payment payment) {
		entityManager.merge(payment);
	}

	
	
	public List<Payment> getReservations() {
		 Account account = SessionUtils.getAccount();
		    Long accountId = account.getId();
		    List<Payment> payments = entityManager.createQuery("SELECT p FROM Payment p WHERE p.account.id = :accountId", Payment.class)
		                                        .setParameter("accountId", accountId)
		                                        .getResultList();

		    
	    if (payments == null) {
	        payments = new ArrayList<>(); // Retourner une liste vide au lieu de null
	    }
	    return payments;
	}
	public int getTotalReservation() {
	    Account account = SessionUtils.getAccount();
	    Long accountId = account.getId();
	    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM Payment p WHERE p.account.id = :accountId", Long.class);
	    query.setParameter("accountId", accountId);
	    Long count = query.getSingleResult();
	    return count.intValue();
	}


}

