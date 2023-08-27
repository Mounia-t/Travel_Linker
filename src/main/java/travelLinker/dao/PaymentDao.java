package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Account;
import travelLinker.entity.Payment;
import travelLinker.utils.SessionUtils;

@Stateless
public class PaymentDao {

	@PersistenceContext
	private EntityManager entityManager;

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
}
