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
import travelLinker.utils.SessionUtils;

import travelLinker.viewModel.AccountViewModel;

@ManagedBean
@SessionScoped
public class AccountControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private AccountDao accountDao;

	public AccountControllerBean() {

	}

	public void addAccount() {
		Long id = accountDao.insert(accountVM);

		// accounts.add();
		// Appeler la méthode insert pour enregistrer l'objet dans la base de données
		// accountDao.insert(accountbean);
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

}
