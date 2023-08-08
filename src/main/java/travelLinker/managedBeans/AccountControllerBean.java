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
	
	
	private AccountViewModel accountVM=new AccountViewModel();
 
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
