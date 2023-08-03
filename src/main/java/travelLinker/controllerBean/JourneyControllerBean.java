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

	// private AccountViewModel accountVM=new AccountViewModel();

	private JourneyBean journeybean = new JourneyBean();
	private List<JourneyBean> journeys = new ArrayList<>();

	private JourneyDao journeyDao = new JourneyDao(entityManager);

	public void addJourney() {

		// Appeler la méthode persist pour enregistrer l'objet dans la base de données
		journeyDao.insert(journeybean);
		journeybean = new JourneyBean();
		// accounts.add(accountBean);
	}
	
	public List<JourneyBean> getJourney() {
		return journeys;
	}

	public void setAccounts(List<JourneyBean> journeys) {
		this.journeys = journeys;
	}

}
