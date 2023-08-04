package travelLinker.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import travelLinker.dao.RestaurantDao;
import travelLinker.viewModel.ServiceViewModel;

@ManagedBean
public class RestaurantControllerBean {

	@Inject
	private RestaurantDao restaurantDao;

	private ServiceViewModel restaurantVm = new ServiceViewModel();

	public void createRestaurant() {
		Long id = restaurantDao.createRestaurant(restaurantVm);
		System.out.println("Restaurant created with id : " + id);
		clear();
	}

	public void clear() {
		restaurantVm = new ServiceViewModel();
	}

	public ServiceViewModel getRestaurantVm() {
		return restaurantVm;
	}

	public void setRestaurantVm(ServiceViewModel restaurantVm) {
		this.restaurantVm = restaurantVm;
	}
}
