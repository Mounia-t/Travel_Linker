package travelLinker.dao;

import travelLinker.entity.JourneyBean;
import travelLinker.viewModel.JourneyViewModel;
	import javax.ejb.Stateless;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;


@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long insert(JourneyViewModel journeyVM) {
		JourneyBean journeybean = new JourneyBean();
		journeybean.setNumberOfTravellers(journeyVM.getNumberOfTravellers());
		journeybean.setPrice(journeyVM.getPrice());
		journeybean.setLocation(journeyVM.getLocation());
		journeybean.setStartDate(journeyVM.getStartDate());
		journeybean.setEndDate(journeyVM.getEndDate());

		entityManager.persist(journeybean);
		entityManager.flush();
		return journeybean.getId();
	}
}
