package travelLinker.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import travelLinker.beans.AccountBean;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class AccountDao {


    private EntityManager entityManager;
	 private static final String JPA_UNIT_NAME = "travelLinker";
	 protected EntityManager getEntityManager() {
	        if (entityManager == null) {
	            entityManager = Persistence.createEntityManagerFactory(
	                    JPA_UNIT_NAME).createEntityManager();
	        }
	        return entityManager;
	    }

	 public AccountBean insert(AccountBean accountbean) {
		    getEntityManager().getTransaction().begin();
		    getEntityManager().persist(accountbean);
		    getEntityManager().getTransaction().commit();
		    return accountbean;}
	 
	 
    // Constructeur public par défaut nécessaire pour les EJBs
    public AccountDao() {
    }

    public AccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(AccountViewModel accountVM) {
        this.entityManager.persist(accountVM);
        this.entityManager.flush();
    }
}