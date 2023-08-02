package travelLinker.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import travelLinker.entity.AccountBean;
import travelLinker.utils.HibernateUtils;

@Stateless

public class LoginDao {
	
	 @PersistenceContext(unitName = "travelLinker")
	    private EntityManager entityManager;
	 
	public LoginDao() {
		
	}  
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

	 
