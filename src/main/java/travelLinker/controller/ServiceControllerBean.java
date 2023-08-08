package travelLinker.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import travelLinker.dao.ServiceDao;
import travelLinker.entity.Accomodation;
import travelLinker.entity.Restaurant;
import travelLinker.viewModel.ServiceViewModel;

@ManagedBean(name = "serviceControllerBean")
@ViewScoped
public class ServiceControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceDao serviceDao;

	private ServiceViewModel restaurantVm = new ServiceViewModel();

	private ServiceViewModel accomodationVm = new ServiceViewModel();

	private boolean showRestaurantForm;

	private boolean showAccomodationForm;

	public List<Accomodation> accomodations;

	public List<Restaurant> restaurants;

	public void createAccomodation() {
		Long id = serviceDao.createAccomodation(accomodationVm);
		System.out.println("Accomodation created with id : " + id);
		clear();
	}

	public void createRestaurant() {
		Long id = serviceDao.createRestaurant(restaurantVm);
		System.out.println("Restaurant created with id : " + id);
		clear();
	}

	public void deleteAccomodation(Long id) {

		serviceDao.deleteAccomodation(id);
		System.out.println("Accomodation deleted with id : " + id);
	}

	public void deleteRestaurant(Long id) {
		serviceDao.deleteRestaurant(id);
		System.out.println("Restaurant deleted with id : " + id);
	}

	public void modifyAccomodation() {
		serviceDao.updateAccomodation(accomodationVm);
		System.out.println("Accomodation updated with id : " + accomodationVm.getId());
		showAccomodationForm = false;
		clear();
	}

	public void setAccomodationVm(Accomodation accomodation) {
		accomodationVm.setId(accomodation.getId());
		accomodationVm.setName(accomodation.getName());
		accomodationVm.setType(accomodation.getType());
		accomodationVm.setPrice(accomodation.getPrice());
		accomodationVm.setCountry(accomodation.getCountry());
		accomodationVm.setLocation(accomodation.getLocation());
		accomodationVm.setStartDate(accomodation.getStartDate());
		accomodationVm.setEndDate(accomodation.getEndDate());
		accomodationVm.setDescription(accomodation.getDescription());
		accomodationVm.setTypeOfAccomodation(accomodation.getTypeOfAccomodation());
		this.showAccomodationForm = true;
	}

	public void modifyRestaurant() {
		serviceDao.updateRestaurant(restaurantVm);
		System.out.println("Restaurant updated with id : " + restaurantVm.getId());
		showRestaurantForm = false;
		clear();
	}

	public void setRestaurantVm(Restaurant restaurant) {
		restaurantVm.setId(restaurant.getId());
		restaurantVm.setName(restaurant.getName());
		restaurantVm.setType(restaurant.getType());
		restaurantVm.setPrice(restaurant.getPrice());
		restaurantVm.setCountry(restaurant.getCountry());
		restaurantVm.setLocation(restaurant.getLocation());
		restaurantVm.setStartDate(restaurant.getStartDate());
		restaurantVm.setEndDate(restaurant.getEndDate());
		restaurantVm.setDescription(restaurant.getDescription());
		restaurantVm.setTypeOfRestaurant(restaurant.getTypeOfRestaurant());
	}

	public ServiceViewModel getRestaurantVm() {
		return restaurantVm;
	}

	public ServiceViewModel getAccomodationVm() {
		return accomodationVm;
	}

	public List<Accomodation> getAccomodations() {
		return serviceDao.getAllAccomodations();
	}

	public List<Restaurant> getRestaurants() {
		return serviceDao.getAllRestaurants();
	}

	public void clear() {
		restaurantVm = new ServiceViewModel();
		accomodationVm = new ServiceViewModel();
	}

	public void updateForm() {
	}

	public boolean isShowAccomodationForm() {
		return showAccomodationForm;
	}

	public void setShowAccomodationForm(boolean showAccomodationForm) {
		this.showAccomodationForm = showAccomodationForm;
	}

	public boolean isShowRestaurantForm() {
		return showRestaurantForm;
	}

	public void setShowRestaurantForm(boolean showRestaurantForm) {
		this.showRestaurantForm = showRestaurantForm;
	}

}
