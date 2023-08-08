package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Accomodation;
import travelLinker.entity.Restaurant;
import travelLinker.viewModel.ServiceViewModel;

@Stateless
public class ServiceDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long createAccomodation(ServiceViewModel accomodationViewModel) {
		Accomodation accomodation = new Accomodation();
		accomodation.setName(accomodationViewModel.getName());
		accomodation.setType(accomodationViewModel.getType());
		accomodation.setPrice(accomodationViewModel.getPrice());
		accomodation.setCountry(accomodationViewModel.getCountry());
		accomodation.setLocation(accomodationViewModel.getLocation());
		accomodation.setStartDate(accomodationViewModel.getStartDate());
		accomodation.setEndDate(accomodationViewModel.getEndDate());
		accomodation.setDescription(accomodationViewModel.getDescription());
		accomodation.setTypeOfAccomodation(accomodationViewModel.getTypeOfAccomodation());
		entityManager.persist(accomodation);
		return accomodation.getId();
	}

	public Long createRestaurant(ServiceViewModel restaurantViewModel) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantViewModel.getName());
		restaurant.setType(restaurantViewModel.getType());
		restaurant.setPrice(restaurantViewModel.getPrice());
		restaurant.setCountry(restaurantViewModel.getCountry());
		restaurant.setLocation(restaurantViewModel.getLocation());
		restaurant.setStartDate(restaurantViewModel.getStartDate());
		restaurant.setEndDate(restaurantViewModel.getEndDate());
		restaurant.setDescription(restaurantViewModel.getDescription());
		restaurant.setTypeOfRestaurant(restaurantViewModel.getTypeOfRestaurant());
		entityManager.persist(restaurant);
		return restaurant.getId();
	}

	public void deleteAccomodation(Long id) {
		Accomodation accomodation = entityManager.find(Accomodation.class, id);
		if (accomodation != null) {
			entityManager.remove(accomodation);
		}
	}

	public void updateAccomodation(ServiceViewModel accomodationViewModel, Long id) {
		Accomodation accomodation = entityManager.find(Accomodation.class, id);
		if (accomodation != null) {

			accomodation.setName(accomodationViewModel.getName());
			accomodation.setType(accomodationViewModel.getType());
			accomodation.setPrice(accomodationViewModel.getPrice());
			accomodation.setCountry(accomodationViewModel.getCountry());
			accomodation.setLocation(accomodationViewModel.getLocation());
			accomodation.setStartDate(accomodationViewModel.getStartDate());
			accomodation.setEndDate(accomodationViewModel.getEndDate());
			accomodation.setDescription(accomodationViewModel.getDescription());
			accomodation.setTypeOfAccomodation(accomodationViewModel.getTypeOfAccomodation());
			entityManager.merge(accomodation);
		}
	}

	public List<Accomodation> getAllAccomodations() {
		return entityManager.createQuery("SELECT a FROM Accomodation a", Accomodation.class).getResultList();
	}

	public List<Restaurant> getAllRestaurants() {
		return entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
	}

}
