package travelLinker.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import travelLinker.entity.Account;
import travelLinker.entity.Journey;
import travelLinker.utils.SessionUtils;
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
	        journeybean.setImagePath(journeyVM.getImagePath()); 
	        System.out.println("Image ajouté" + journeyVM.getImagePath());
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
	    List<Journey> journeys = entityManager.createQuery("SELECT j FROM Journey j", Journey.class)
	                                        .getResultList();
	    if (journeys == null) {
	        journeys = new ArrayList<>(); // Retourner une liste vide au lieu de null
	    }
	    return journeys;
	}


	public List<Journey> getTravelPlannerJourneys() {
	    Account account = SessionUtils.getAccount();
	    Long accountId = account.getId();
	    List<Journey> listJourney = entityManager.createQuery("SELECT j FROM Journey j WHERE j.accountId = :accountId", Journey.class)
	                                            .setParameter("accountId", accountId)
	                                            .getResultList();
	    
	    if (listJourney == null) {
	        listJourney = new ArrayList<>(); // Retourner une liste vide au lieu de null
	    }
	    
	    System.out.println(listJourney);
	    return listJourney;

	}

	}
		
	