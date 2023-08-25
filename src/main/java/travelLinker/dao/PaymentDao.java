package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Payment;

@Stateless
public class PaymentDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long createPayment(Payment payment) {
		entityManager.persist(payment);
		return payment.getId();
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
