package travelLinker.dao;

	import javax.ejb.Stateless;
	import javax.inject.Inject;
	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;
	import javax.persistence.PersistenceContext;


import travelLinker.beans.JourneyBean;
import travelLinker.viewModel.JourneyViewModel;

	@Stateless
	public class JourneyDao {


	    private EntityManager entityManager;
		 private static final String JPA_UNIT_NAME = "travelLinker";
		 protected EntityManager getEntityManager() {
		        if (entityManager == null) {
		            entityManager = Persistence.createEntityManagerFactory(
		                    JPA_UNIT_NAME).createEntityManager();
		        }
		        return entityManager;
		    }

		 public JourneyBean insert(JourneyBean journeybean) {
			    getEntityManager().getTransaction().begin();
			    getEntityManager().persist(journeybean);
			    getEntityManager().getTransaction().commit();
			    return journeybean;}
		 
		 
	    // Constructeur public par défaut nécessaire pour les EJBs
	    public JourneyDao() {
	    }

	    public JourneyDao(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    public void persist (JourneyViewModel journeyVM) {
	        this.entityManager.persist(journeyVM);
	        this.entityManager.flush();
	    }
	}
