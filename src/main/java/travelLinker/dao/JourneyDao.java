package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Journey;
import travelLinker.viewModel.JourneyViewModel;

@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long insert(JourneyViewModel journeyVM) {
		Journey journeybean = new Journey();
		journeybean.setNumberOfTravellers(journeyVM.getNumberOfTravellers());
		journeybean.setPrice(journeyVM.getPrice());
		journeybean.setLocation(journeyVM.getLocation());
		journeybean.setStartDate(journeyVM.getStartDate());
		journeybean.setEndDate(journeyVM.getEndDate());

		entityManager.persist(journeybean);
		entityManager.flush();
		return journeybean.getId();
	}

	public Journey findByIdJourney(Long id) {
		return entityManager.find(Journey.class, id);
	}
}
