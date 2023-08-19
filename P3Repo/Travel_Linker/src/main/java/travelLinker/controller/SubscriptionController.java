package travelLinker.controller;


import travelLinker.entity.Account;
import travelLinker.dao.AccountDao;
import travelLinker.dao.SubscriptionDao;
import travelLinker.entity.Subscription;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.SubscriptionViewModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
public class SubscriptionController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private SubscriptionDao subsDao; 
    
    private Account account; // Utilisateur actuellement connecté
   
    @Inject
    private AccountDao accountDao;
    
    @PostConstruct
    public void init() {
        loadSubscriptionsForUser();
    }
    private SubscriptionViewModel newSubscription = new SubscriptionViewModel();
    private List<Subscription> subscriptions;
    private EntityManager entityManager;

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
    
    public void loadSubscriptionsForUser() {
        // Obtenez l'ID de l'utilisateur actuellement connecté à partir de la session
        Long loggedInAccountId = SessionUtils.getUserId();

        if (loggedInAccountId != null) {
            // Obtenez l'objet Account associé à partir de la base de données en utilisant l'ID
            Account loggedInAccount = accountDao.getAccountById(loggedInAccountId);

            if (loggedInAccount != null) {
                // Chargez les abonnements pour l'utilisateur connecté
                subscriptions = subsDao.getSubscriptionsByAccount(loggedInAccount);
            } else {
                // L'utilisateur n'est pas trouvé, gérer selon vos besoins
                subscriptions = new ArrayList<>();
            }
        } else {
            // L'utilisateur n'est pas connecté, gérer selon vos besoins
            subscriptions = new ArrayList<>();
        }
    }
    public void subscribeToFormule(SubscriptionViewModel selectedSubscription) {
        Subscription subscription = new Subscription();
        subscription.setAccount(account);
        subscription.setPrice(selectedSubscription.getPrice());
        subscription.setStartDate(selectedSubscription.getStartDate());
        subscription.setEndDate(selectedSubscription.getEndDate());
        subscription.setType(selectedSubscription.getType());

        subsDao.createSubscription(subscription);
        subscriptions.add(subscription); // Ajoutez la nouvelle formule à la liste
    }
    
   
}
