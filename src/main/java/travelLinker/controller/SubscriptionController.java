package travelLinker.controller;


import travelLinker.entity.Account;
import travelLinker.dao.AccountDao;
import travelLinker.dao.SubscriptionDao;
import travelLinker.entity.Subscription;
import travelLinker.entity.SubscriptionPack;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.SubscriptionViewModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.io.IOException;
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
    
    @Inject
    private AccountControllerBean accountControllerBean;
    
    private String selectedSubscription;
    
    private List<SubscriptionPack> pack;
    private SubscriptionViewModel newSubscription = new SubscriptionViewModel();
   
    
    public void createSubscription(SubscriptionPack selectedPack) {
        if (account == null) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not logged in", "Please log in to create a subscription."));
            return;
        }

        if (!accountControllerBean.isUserTravelPlanner(account.getId())) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access Denied", "Only travelplanners can create subscriptions."));
            return;
        }
        
        else {

        Subscription subscription = new Subscription();
        subscription.setAccount(account);
        subscription.setPrice(selectedPack.getPrice()); 
        subscription.setStartDate(newSubscription.getStartDate());
        subscription.setEndDate(newSubscription.getEndDate());
        subscription.setType(selectedPack);

        
        subsDao.save(subscription);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Subscription created", "Your subscription has been successfully created."));
    }
    }
    
    public List<Subscription> getAllSubscriptions() {
       return subsDao.getAllSubscriptions();
    }

	public String dispSelectedSubsPack(String type) {
		 selectedSubscription =type;
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 try {
		        externalContext.redirect("PaymentForm.xhtml");
		    } catch (IOException e) {
		        // Handle the exception if redirection fails
		        e.printStackTrace();
		    }
		 System.out.println(selectedSubscription);
		 return selectedSubscription;
	}

	public String getSelectedSubscription() {
		return selectedSubscription;
	}

	public void setSelectedSubscription(String selectedSubscription) {
		this.selectedSubscription = selectedSubscription;
	}
    
   
}
