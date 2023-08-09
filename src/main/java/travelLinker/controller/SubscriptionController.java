package travelLinker.controller;


import travelLinker.entity.Account;
import travelLinker.dao.SubscriptionDao;
import travelLinker.entity.Subscription;
import travelLinker.viewModel.SubscriptionViewModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import java.io.Serializable;
import java.util.List;


@ManagedBean
public class SubscriptionController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private SubscriptionDao subsDao; // Injectez votre service d'abonnement
    
    private Account account; // Utilisateur actuellement connecté
 

    private SubscriptionViewModel newSubscription = new SubscriptionViewModel();
    private List<Subscription> subscriptions;
    public void loadSubscriptionsForUser() {
        subscriptions = subsDao.getSubscriptionsByAccount(account);
    }
    

    public SubscriptionViewModel getNewSubscription() {
        return newSubscription;
        }

    public void createSubscription() {
        Subscription subscription = new Subscription();
        subscription.setAccount(account);
        subscription.setPrice(newSubscription.getPrice());
        subscription.setStartDate(newSubscription.getStartDate());
        subscription.setEndDate(newSubscription.getEndDate());
        subscription.setType(newSubscription.getType());

        subsDao.createSubscription(subscription);
        newSubscription = new SubscriptionViewModel(); // Reset the form
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}
