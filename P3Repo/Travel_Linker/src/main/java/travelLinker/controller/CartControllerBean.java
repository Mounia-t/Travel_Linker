package travelLinker.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.CartDao;
import travelLinker.dao.JourneyDao;
import travelLinker.dao.ServiceDao;
import travelLinker.entity.Cart;
import travelLinker.entity.Item;
import travelLinker.entity.RoleUser;
import travelLinker.viewModel.ItemViewModel;

@ManagedBean
@SessionScoped
public class CartControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CartDao cartDao;

	@Inject
	private ServiceDao serviceDao;

	@Inject
	private JourneyDao journeyDao;

	public void addItemToCart(Long cartId, ItemViewModel itemViewModel) {
		Item item = new Item();
		item.setId(itemViewModel.getId());
		item.setQuantity(itemViewModel.getQuantity());
		item.setStartDate(itemViewModel.getStartDate());
		item.setEndDate(itemViewModel.getEndDate());
		item.setPrice(itemViewModel.getPrice());
		item.setItemType(itemViewModel.getItemType());

		if (itemViewModel.getJourneyId() != null) {
			item.setJourney(journeyDao.findByIdJourney(itemViewModel.getJourneyId()));
		}
		if (itemViewModel.getAccomodationId() != null) {
			item.setAccomodation(serviceDao.findByIdAccomodation(itemViewModel.getAccomodationId()));
		}
		if (itemViewModel.getRestaurantId() != null) {
			item.setRestaurant(serviceDao.findByIdRestaurant(itemViewModel.getRestaurantId()));
		}
		if (itemViewModel.getTransportId() != null) {
			item.setTransport(serviceDao.findByIdTransport(itemViewModel.getTransportId()));
		}
		cartDao.addItemToCart(cartId, item);
	}

	public void removeItemFromCart(Long cartId, Long itemId) {
		cartDao.removeItemFromCart(cartId, itemId);
	}

	public void clearCart(Long cartId) {
		cartDao.clearCart(cartId);
	}

	public Cart getCartByUserId(Long userId, RoleUser userType) {
		switch (userType) {
		case Customer:
			return cartDao.getCartByCustomerId(userId);
		case Partner:
			return cartDao.getCartByPartnerId(userId);
		case TravelPlanner:
			return cartDao.getCartByTravelPlannerId(userId);
		default:
			return null;
		}
	}

}
