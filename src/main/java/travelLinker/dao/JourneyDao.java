package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelLinker.entity.Journey;
import travelLinker.entity.Service;
import travelLinker.entity.Task;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.JourneyViewModel;

@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long insert(JourneyViewModel journeyVM) {
	    try {
	        Journey journeybean = new Journey();
	        Long accountId = SessionUtils.getUserId();
	        journeybean.setAccountId(accountId);
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
		}
	}
	
	public void updateJourney(JourneyViewModel journeyViewModel) {
		Journey journey = entityManager.find(Journey.class, journeyViewModel.getId());
		if (journey != null) {

			journey.setNumberOfTravellers(journeyViewModel.getNumberOfTravellers());
			journey.setName(journeyViewModel.getName());
			journey.setImageFile(journeyViewModel.getImage());
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
	public List<Journey>getTravelPlannerJourneys(){
		Long accountId = SessionUtils.getUserId();
		List<Journey> ListJourney=entityManager.createQuery("SELECT j FROM Journey j WHERE j.accountId = :accountId", Journey.class)
	            .setParameter("accountId", accountId)
	            .getResultList();
		System.out.println(ListJourney);
	    return ListJourney;
	}
	
		
	}
		
	
