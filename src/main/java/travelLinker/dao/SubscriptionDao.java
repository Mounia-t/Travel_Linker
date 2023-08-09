package travelLinker.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Account;
import travelLinker.entity.Subscription;

@Stateless
public class SubscriptionDao{
    @PersistenceContext
    private EntityManager entityManager;
   

    public Subscription createSubscription(Subscription subscription) {
        entityManager.persist(subscription);
        return subscription;
    }

    public Subscription updateSubscription(Subscription subscription) {
        return entityManager.merge(subscription);
    }

    public Subscription getSubscriptionById(Long id) {
        return entityManager.find(Subscription.class, id);
    }

    public List<Subscription> getSubscriptionsByAccount(Account account) {
        return entityManager.createQuery("SELECT s FROM Subscription s WHERE s.account = :account", Subscription.class)
                .setParameter("account", account)
                .getResultList();
    }
}