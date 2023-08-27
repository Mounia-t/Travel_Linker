package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import travelLinker.entity.Subscription;
import travelLinker.entity.SubscriptionPack;

@Stateless
public class SubscriptionDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Subscription subscription) {
		entityManager.persist(subscription);
	}

	public void addSubscriptionPacks() {
		if (!packAbonnementExiste(SubscriptionPack.Essentiel)) {
			Subscription essentialPack = new Subscription();
			essentialPack.setType(SubscriptionPack.Essentiel);
			essentialPack.setPrice(50);
			essentialPack.setDuration(30);
			entityManager.persist(essentialPack);
		}

		if (!packAbonnementExiste(SubscriptionPack.Extra)) {
			Subscription extraPack = new Subscription();
			extraPack.setType(SubscriptionPack.Extra);
			extraPack.setPrice(75);
			extraPack.setDuration(60);
			entityManager.persist(extraPack);
		}

		if (!packAbonnementExiste(SubscriptionPack.Premium)) {
			Subscription premiumPack = new Subscription();
			premiumPack.setType(SubscriptionPack.Premium);
			premiumPack.setPrice(100);
			premiumPack.setDuration(90);
			entityManager.persist(premiumPack);
		}
	}

	private boolean packAbonnementExiste(SubscriptionPack type) {
		TypedQuery<Subscription> query = entityManager.createQuery("SELECT s FROM Subscription s WHERE s.type = :type",
				Subscription.class);
		query.setParameter("type", type);
		return !query.getResultList().isEmpty();
	}

	public List<Subscription> getAllSubscriptions() {
		addSubscriptionPacks();
		TypedQuery<Subscription> query = entityManager.createQuery("SELECT s FROM Subscription s", Subscription.class);
		return query.getResultList();
	}

	public Subscription getSubscriptionById(Long id) {
		return entityManager.find(Subscription.class, id);
	}

}