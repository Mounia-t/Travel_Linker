package travelLinker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Account;
import travelLinker.entity.Journey;
import travelLinker.entity.Restaurant;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.JourneyViewModel;

@Stateless
public class JourneyDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	ServiceDao serviceDao;

	public Long insert(JourneyViewModel journeyVM, List<Restaurant> selectedRestaurants) {

		try {
			Journey journeybean = new Journey();

			Account account = SessionUtils.getAccount();
			Long accountId = account.getId();

			// Utilisez plutôt directement journeybean.setAccount(account);
			journeybean.setAccount(account);

			journeybean.setNumberOfTravellers(journeyVM.getNumberOfTravellers());
			journeybean.setPrice(journeyVM.getPrice());
			journeybean.setLocation(journeyVM.getLocation());
			journeybean.setCountry(journeyVM.getCountry());
			journeybean.setStartDate(journeyVM.getStartDate());
			journeybean.setEndDate(journeyVM.getEndDate());
			journeybean.setDescription(journeyVM.getDescription());
			journeybean.setImagePath(journeyVM.getImagePath()); // Utilisez le chemin de l'image

			// Récupérer la liste des restaurants sélectionnés
			System.out.println("ma selec dans journeyDao insert" + selectedRestaurants);

			// Associer la liste des restaurants sélectionnés au voyage
//	        journeybean.setSelectedRestaurants(selectedRestaurants);

			// Persistez l'entité Journey
			entityManager.persist(journeybean);
			entityManager.flush();

			return journeybean.getId();
		} catch (Exception e) {
			e.printStackTrace(); // Gérez les exceptions de manière appropriée
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
		List<Journey> journeys = entityManager.createQuery("SELECT j FROM Journey j", Journey.class).getResultList();
		if (journeys == null) {
			journeys = new ArrayList<>(); // Retourner une liste vide au lieu de null
		}
		return journeys;
	}

	public List<Journey> getTravelPlannerJourneys() {
		Account account = SessionUtils.getAccount();
		Long accountId = account.getId();
		List<Journey> listJourney = entityManager
				.createQuery("SELECT j FROM Journey j WHERE j.accountId = :accountId", Journey.class)
				.setParameter("accountId", accountId).getResultList();

		if (listJourney == null) {
			listJourney = new ArrayList<>(); // Retourner une liste vide au lieu de null
		}

		System.out.println(listJourney);
		return listJourney;

	}

}
