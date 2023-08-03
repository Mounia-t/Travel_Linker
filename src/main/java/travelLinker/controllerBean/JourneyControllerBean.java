package travelLinker.controllerBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import travelLinker.beans.JourneyBean;
import travelLinker.dao.JourneyDao;

@ManagedBean
public class JourneyControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	private JourneyBean journeybean = new JourneyBean();
	private List<JourneyBean> journeys = new ArrayList<>();

	private JourneyDao journeyDao = new JourneyDao(entityManager);

	public void addJourney() {

		// Appeler la méthode persist pour enregistrer l'objet dans la base de données
		journeyDao.insert(journeybean);
		journeybean = new JourneyBean();

	}
	
	public List<JourneyBean> getJourney() {
		return journeys;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public JourneyBean getJourneybean() {
		return journeybean;
	}

	public List<JourneyBean> getJourneys() {
		return journeys;
	}

	public JourneyDao getJourneyDao() {
		return journeyDao;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setJourneybean(JourneyBean journeybean) {
		this.journeybean = journeybean;
	}

	public void setJourneys(List<JourneyBean> journeys) {
		this.journeys = journeys;
	}

	public void setJourneyDao(JourneyDao journeyDao) {
		this.journeyDao = journeyDao;
	}



	public void setAccounts(List<JourneyBean> journeys) {
		this.journeys = journeys;
	}

}
