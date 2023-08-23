package travelLinker.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import travelLinker.entity.Journey;
import travelLinker.viewModel.JourneyViewModel;

@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long insert(JourneyViewModel journeyVM) {

	    try {
	        Journey journeybean = new Journey();
	        journeybean.setNumberOfTravellers(journeyVM.getNumberOfTravellers());
	        journeybean.setPrice(journeyVM.getPrice());
	        journeybean.setLocation(journeyVM.getLocation());
	        journeybean.setCountry(journeyVM.getCountry());
	        journeybean.setStartDate(journeyVM.getStartDate());
	        journeybean.setEndDate(journeyVM.getEndDate());
	        journeybean.setName(journeyVM.getName());
	        journeybean.setDescription(journeyVM.getDescription());
	        journeybean.setImagePath(journeyVM.getImagePath()); // Utilisez le chemin de l'image

	        entityManager.persist(journeybean);
	        entityManager.flush();
	        return journeybean.getId();
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle exceptions appropriately
	        return null;
	    }
	}

	public void deleteJourney(Long id) {
	    Journey journey = entityManager.find(Journey.class, id);
	    if (journey != null) {
	        entityManager.remove(journey);
	    } else {
	        System.out.println("Journey introuvable, suppression impossible");
	    }
	}

	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void updateJourney(JourneyViewModel journeyViewModel) {
		Journey journey = entityManager.find(Journey.class, journeyViewModel.getId());
		if (journey != null) {

			journey.setNumberOfTravellers(journeyViewModel.getNumberOfTravellers());
			journey.setName(journeyViewModel.getName());
			journey.setPrice(journeyViewModel.getPrice());
			journey.setCountry(journeyViewModel.getCountry());
			journey.setLocation(journeyViewModel.getLocation());
			journey.setStartDate(journeyViewModel.getStartDate());
			journey.setEndDate(journeyViewModel.getEndDate());

			entityManager.merge(journey);
		}
	}
	

	public Journey findByIdJourney(Long id) {
		return entityManager.find(Journey.class, id);
	}
	
	public List<Journey> getAllJourneys() {
		return entityManager.createQuery("SELECT j FROM Journey j", Journey.class).getResultList();
	}

	public Journey getJourneyById(Long journeyId) {
	    return entityManager.find(Journey.class, journeyId);
	}
}
