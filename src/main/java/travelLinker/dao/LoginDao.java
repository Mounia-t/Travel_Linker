package travelLinker.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import travelLinker.entity.AccountBean;
import travelLinker.utils.HibernateUtils;

@Stateless
@LocalBean
public class LoginDao {
	
	 public static void setEntityManager(EntityManager entityManager) {
		LoginDao.entityManager = entityManager;
	}
	 @Inject
	private static EntityManager entityManager;
	 
	public LoginDao() {
		
	}

	   
	 /*protected EntityManager getEntityManager() {
	        if (entityManager == null) {
	            entityManager = Persistence.createEntityManagerFactory(
	                    HibernateUtils.getJpaUnitName()).createEntityManager();
	        }
	        return entityManager;
	    }*/
	public LoginDao(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	public static boolean validate(String email, String password) {
       EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory(HibernateUtils.getJpaUnitName());
            em = emf.createEntityManager();

            AccountBean accountBean = entityManager.find(AccountBean.class, email);

            if (accountBean != null && accountBean.getPassword().equals(password)) {
              
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
      
	}
		return false;
}

}

	 
