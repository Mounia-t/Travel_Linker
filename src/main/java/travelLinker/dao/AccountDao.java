package travelLinker.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import travelLinker.entity.AccountBean;
import travelLinker.viewModel.AccountViewModel;

@Stateless

public class AccountDao {

    @PersistenceContext(unitName = "travelLinker")
    private EntityManager entityManager;

    public Long insert(AccountViewModel accountVM) {
    	AccountBean accountbean = new AccountBean();
    	accountbean.setEmail(accountVM.getEmail());
    	accountbean.setPassword(accountVM.getPassword());
        entityManager.persist(accountbean);
        entityManager.flush();
        return accountbean.getId();
    }
	 
    public AccountDao() {
    }

    /*public void persist(AccountViewModel accountVM) {
        this.entityManager.persist(accountVM);
        this.entityManager.flush();
    }*/
}