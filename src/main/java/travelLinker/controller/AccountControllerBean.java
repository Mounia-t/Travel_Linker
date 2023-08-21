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

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private AccountDao accountDao;


    private List<Partner> partners;
    private List<Partner> filteredPartners;
    private String searchKeyword;

	/*public AccountControllerBean() {

	}
*/
	/*public void addAccount() {
		Long id = accountDao.insert(accountVM);

		// accounts.add();
		// Appeler la méthode insert pour enregistrer l'objet dans la base de données
		// accountDao.insert(accountbean);
		accountVM = new AccountViewModel();*/

	
	public void addPartner() {
		accountDao.createPartner(accountVM);
		accountVM = new AccountViewModel();
	}
	
	public void addCustomer() {
		accountDao.createCustomer(accountVM);
		accountVM = new AccountViewModel();
	}
	
	public void addTravelP() {
		accountDao.createTravelPlanner(accountVM);
		accountVM = new AccountViewModel();
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
	public int countTotalPartners() {
		return accountDao.getTotalPartnersCount();

	}

	public List<Partner> getPartners() {
		return accountDao.displayPartners();
	}

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
	public List<Partner>getNewPartner(){
		return accountDao.getLatestRegisteredPartners(4);
		
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
	
	public List<Partner> getFilteredPartners() {
		return filteredPartners;
	}

	public void setFilteredPartners(List<Partner> filteredPartners) {
		this.filteredPartners = filteredPartners;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}


public void searchPartners() {
    filteredPartners = new ArrayList<>();
    for (Partner partner : partners) {
        if (partner.getFirstName().toLowerCase().contains(searchKeyword.toLowerCase())) {
            filteredPartners.add(partner);
        }
    }
}}