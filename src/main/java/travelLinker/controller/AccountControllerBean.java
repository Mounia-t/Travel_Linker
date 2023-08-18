package travelLinker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import travelLinker.dao.AccountDao;

import travelLinker.entity.Account;
import travelLinker.entity.Partner;
import travelLinker.entity.RoleUser;
import travelLinker.utils.SessionUtils;

import travelLinker.viewModel.AccountViewModel;

@ManagedBean
@SessionScoped
public class AccountControllerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private AccountViewModel accountVM=new AccountViewModel();
	private Long accountId;
 
    @Inject
    private AccountDao accountDao;

 
    public AccountControllerBean() {
	
	}

	public void addAccount() {
    	Long id=accountDao.insert(accountVM);

        //accounts.add();
        // Appeler la méthode insert pour enregistrer l'objet dans la base de données
        	//accountDao.insert(accountbean);
        	accountVM=new AccountViewModel();

    }

	public List<Partner> getPartners() {
        return accountDao.displayPartners();
    }

//---------------------------------------------------	
	public void deleteAccount() {
        // Vérifier si l'utilisateur est connecté (authentifié)
        Long accountId = SessionUtils.getUserId();
        
        if (accountId != null) {
            // On appelle la méthode de suppression dans le DAO pour supprimer le compte
            accountDao.delete(accountId);
        } else {
            System.out.println("L'utilisateur n'est pas connecté.");
        }
    }	
		
//----------------------------------------------------------	

	public void updateAccount() {
	    // Vérifier si l'utilisateur est connecté (authentifié)
	    Long accountId = SessionUtils.getUserId();
	        
	    if (accountId != null) {
	        // Créer un nouvel objet AccountBean avec les valeurs mises à jour
	        Account updatedAccount = new Account();
	        updatedAccount.setId(accountId);
	        updatedAccount.setFirstName(accountVM.getFirstName());
	        updatedAccount.setLastName(accountVM.getLastName());
	        updatedAccount.setEmail(accountVM.getEmail());
	        updatedAccount.setPassword(accountVM.getPassword());
	        updatedAccount.setRole(accountVM.getRole());
	        
	        // Appeler la méthode de mise à jour dans le DAO pour mettre à jour le compte
	        accountDao.update(updatedAccount);
	    } else {
	        System.out.println("L'utilisateur n'est pas connecté.");
	    }
	}
//--------------------------------------------------------
	
	
	
	
	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
//----------------------------------------------------------
	public boolean isUserTravelPlanner(Long userId) {
        RoleUser userRole = accountDao.getUserRoleById(userId);
        if (userRole == null) {
            // Le rôle de l'utilisateur n'a pas été trouvé
            return false;
        }
        
        return userRole == RoleUser.TravelPlanner;
   
    }
	
}

	