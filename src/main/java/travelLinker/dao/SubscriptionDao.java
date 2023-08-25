package travelLinker.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import travelLinker.entity.Account;
import travelLinker.entity.Subscription;
import travelLinker.entity.SubscriptionPack;

@Stateless
public class SubscriptionDao{
    @PersistenceContext
    private EntityManager entityManager;
   
    Account account;
    
    public void save(Subscription subscription) {
        entityManager.persist(subscription);
    }

    public void addSubscriptionPacks() {
        // Create and insert the "Essential" pack
        Subscription essentialPack = new Subscription();
        essentialPack.setType(SubscriptionPack.Essentiel);;
        essentialPack.setPrice(50);
        essentialPack.setDuration(30);
        entityManager.persist(essentialPack);

        // Create and insert the "Extra" pack
        Subscription extraPack = new Subscription();
        extraPack.setType(SubscriptionPack.Extra);
        extraPack.setPrice(75);
        extraPack.setDuration(60);
        entityManager.persist(extraPack);

        // Create and insert the "Premium" pack
        Subscription premiumPack = new Subscription();
        premiumPack.setType(SubscriptionPack.Premium);
        premiumPack.setPrice(100);
        premiumPack.setDuration(90);
        entityManager.persist(premiumPack);
    }
    
    public List<Subscription> getAllSubscriptions() {
    	//addSubscriptionPacks(); 
        TypedQuery<Subscription> query = entityManager.createQuery("SELECT s FROM Subscription s", Subscription.class);
        return query.getResultList();
    }

}