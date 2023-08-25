package travelLinker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Accomodation;
import travelLinker.entity.Restaurant;
import travelLinker.entity.Service;
import travelLinker.entity.Transport;
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

	public Long createTransport(ServiceViewModel transportViewModel) {
		Transport transport = new Transport();
		transport.setName(transportViewModel.getName());
		transport.setType(transportViewModel.getType());
		transport.setPrice(transportViewModel.getPrice());
		transport.setCountry(transportViewModel.getCountry());
		transport.setLocation(transportViewModel.getLocation());
		transport.setStartDate(transportViewModel.getStartDate());
		transport.setEndDate(transportViewModel.getEndDate());
		transport.setDescription(transportViewModel.getDescription());
		transport.setModeOfTransport(transportViewModel.getModeOfTransport());
		entityManager.persist(transport);
		return transport.getId();
	}

	public void deleteAccomodation(Long id) {
		Accomodation accomodation = entityManager.find(Accomodation.class, id);
		if (accomodation != null) {
			entityManager.remove(accomodation);
		}
	}

	public void deleteRestaurant(Long id) {
		Restaurant restaurant = entityManager.find(Restaurant.class, id);
		if (restaurant != null) {
			entityManager.remove(restaurant);
		}
	}

	public void deleteTransport(Long id) {
		Transport transport = entityManager.find(Transport.class, id);
		if (transport != null) {
			entityManager.remove(transport);
		}
	}

	public void updateAccomodation(ServiceViewModel accomodationViewModel) {
		Accomodation accomodation = entityManager.find(Accomodation.class, accomodationViewModel.getId());
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

	public void updateRestaurant(ServiceViewModel restaurantViewModel) {
		Restaurant restaurant = entityManager.find(Restaurant.class, restaurantViewModel.getId());
		if (restaurant != null) {

			restaurant.setName(restaurantViewModel.getName());
			restaurant.setType(restaurantViewModel.getType());
			restaurant.setPrice(restaurantViewModel.getPrice());
			restaurant.setCountry(restaurantViewModel.getCountry());
			restaurant.setLocation(restaurantViewModel.getLocation());
			restaurant.setStartDate(restaurantViewModel.getStartDate());
			restaurant.setEndDate(restaurantViewModel.getEndDate());
			restaurant.setDescription(restaurantViewModel.getDescription());
			restaurant.setTypeOfRestaurant(restaurantViewModel.getTypeOfRestaurant());
			entityManager.merge(restaurant);
		}
	}

	public void updateTransport(ServiceViewModel transportViewModel) {
		Transport transport = entityManager.find(Transport.class, transportViewModel.getId());
		if (transport != null) {

			transport.setName(transportViewModel.getName());
			transport.setType(transportViewModel.getType());
			transport.setPrice(transportViewModel.getPrice());
			transport.setCountry(transportViewModel.getCountry());
			transport.setLocation(transportViewModel.getLocation());
			transport.setStartDate(transportViewModel.getStartDate());
			transport.setEndDate(transportViewModel.getEndDate());
			transport.setDescription(transportViewModel.getDescription());
			transport.setModeOfTransport(transportViewModel.getModeOfTransport());
			entityManager.merge(transport);
		}
	}

	public List<Accomodation> getAllAccomodations() {
		return entityManager.createQuery("SELECT a FROM Accomodation a", Accomodation.class).getResultList();
	}

	public List<Restaurant> getAllRestaurants() {
		return entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
	}

	public List<Transport> getAllTransports() {
		return entityManager.createQuery("SELECT t FROM Transport t", Transport.class).getResultList();
	}

	public Accomodation findByIdAccomodation(Long id) {
		return entityManager.find(Accomodation.class, id);
	}

	public Restaurant findByIdRestaurant(Long id) {
		return entityManager.find(Restaurant.class, id);
	}

	public Transport findByIdTransport(Long id) {
		return entityManager.find(Transport.class, id);
	}
	
	
	public List<Service> getAllServices() {
		List<Service> services = new ArrayList<>();
		services.addAll(getAllAccomodations());
		services.addAll(getAllRestaurants());
		services.addAll(getAllTransports());

		return services;
	}
	
	public List<Service> displayFiltredServices(String country){
		List<Service> servicesFiltred=  entityManager.createQuery("SELECT s FROM Service s WHERE s.country = :country", Service.class)
		            .setParameter("country", country)
		            .getResultList();
		return servicesFiltred;
}
}