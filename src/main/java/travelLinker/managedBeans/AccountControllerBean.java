package travelLinker.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import travelLinker.dao.AccountDao;
import travelLinker.entity.AccountBean;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
public class AccountControllerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	//private AccountViewModel accountVM=new AccountViewModel();
	
	private AccountBean accountbean=new AccountBean();
    private List<AccountBean> accounts = new ArrayList<>();
    
    private AccountDao accountDao = new AccountDao(entityManager);

    public void addAccount() {
    	
        // Appeler la méthode persist pour enregistrer l'objet dans la base de données
        	accountDao.insert(accountbean);
        	accountbean = new AccountBean();
        //accounts.add(accountBean);
    }

/*	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}*/

	public List<AccountBean> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountBean> accounts) {
		this.accounts = accounts;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public AccountBean getAccountbean() {
		return accountbean;
	}

	public void setAccountbean(AccountBean accountbean) {
		this.accountbean = accountbean;
	}
    
	
	
}
