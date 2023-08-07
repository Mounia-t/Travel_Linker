package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import travelLinker.entity.JourneyBean;
import travelLinker.viewModel.JourneyViewModel;

@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long insert(JourneyViewModel journeyVM) {
		JourneyBean journeybean = new JourneyBean();
		journeybean.setCountry(journeyVM.getCountry());
		journeybean.setNumberOfTravellers(journeyVM.getNumberOfTravellers());
		journeybean.setPrice(journeyVM.getPrice());
		journeybean.setLocation(journeyVM.getLocation());
		journeybean.setStartDate(journeyVM.getStartDate());
		journeybean.setEndDate(journeyVM.getEndDate());

		entityManager.persist(journeyVM);
		entityManager.flush();
		return journeybean.getId();
	}

}
