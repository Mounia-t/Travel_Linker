package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Restaurant;
import travelLinker.viewModel.ServiceViewModel;

@Stateless
public class RestaurantDao {

	@PersistenceContext
	private EntityManager entityManager;

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
}
