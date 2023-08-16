package travelLinker.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.ServiceDao;
import travelLinker.entity.Accomodation;
import travelLinker.entity.Restaurant;
import travelLinker.entity.Transport;
import travelLinker.viewModel.ServiceViewModel;

@ManagedBean
@SessionScoped
public class ServiceControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceDao serviceDao;

	private ServiceViewModel restaurantVm = new ServiceViewModel();

	private ServiceViewModel accomodationVm = new ServiceViewModel();

	private ServiceViewModel transportVm = new ServiceViewModel();

	private boolean showRestaurantForm;

	private boolean showAccomodationForm;

	private boolean showTransportForm;

	public List<Accomodation> accomodations;

	public List<Restaurant> restaurants;

	public List<Transport> transports;

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

	public void createTransport() {
		Long id = serviceDao.createTransport(transportVm);
		System.out.println("Transport created with id : " + id);
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

	public void deleteTransport(Long id) {
		serviceDao.deleteTransport(id);
		System.out.println("Transport deleted with id : " + id);
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
		showAccomodationForm = true;
		showTransportForm = false;
		showRestaurantForm = false;
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
		showRestaurantForm = true;
		showAccomodationForm = false;
		showTransportForm = false;
	}

	public void modifyTransport() {
		serviceDao.updateTransport(transportVm);
		System.out.println("Transport updated with id : " + transportVm.getId());
		showTransportForm = false;
		clear();
	}

	public void setTransportVm(Transport transport) {
		System.out.println("clicked");
		transportVm.setId(transport.getId());
		transportVm.setName(transport.getName());
		transportVm.setType(transport.getType());
		transportVm.setPrice(transport.getPrice());
		transportVm.setCountry(transport.getCountry());
		transportVm.setLocation(transport.getLocation());
		transportVm.setStartDate(transport.getStartDate());
		transportVm.setEndDate(transport.getEndDate());
		transportVm.setDescription(transport.getDescription());
		transportVm.setModeOfTransport(transport.getModeOfTransport());
		showTransportForm = true;
		showRestaurantForm = false;
		showAccomodationForm = false;
	}

	public void clear() {
		restaurantVm = new ServiceViewModel();
		accomodationVm = new ServiceViewModel();
		transportVm = new ServiceViewModel();
	}

	public void updateForm() {
	}

	public List<Accomodation> getAccomodations() {
		return serviceDao.getAllAccomodations();
	}

	public List<Restaurant> getRestaurants() {
		return serviceDao.getAllRestaurants();
	}

	public List<Transport> getTransports() {
		return serviceDao.getAllTransports();
	}

	public ServiceViewModel getRestaurantVm() {
		return restaurantVm;
	}

	public ServiceViewModel getAccomodationVm() {
		return accomodationVm;
	}

	public ServiceViewModel getTransportVm() {
		return transportVm;
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

	public boolean isShowTransportForm() {
		return showTransportForm;
	}

	public void setShowTransportForm(boolean showTransportForm) {
		this.showTransportForm = showTransportForm;
	}

}
