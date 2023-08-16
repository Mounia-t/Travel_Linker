package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Cart;
import travelLinker.entity.Customer;
import travelLinker.entity.Item;
import travelLinker.entity.Partner;
import travelLinker.entity.TravelPlanner;

@Stateless
public class CartDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Cart createCartCustomer(Customer customer) {
		Cart cart = new Cart();
		cart.setCustomer(customer);
		entityManager.persist(cart);
		return cart;
	}

	public Cart createCartPartner(Partner partner) {
		Cart cart = new Cart();
		cart.setPartner(partner);
		entityManager.persist(cart);
		return cart;
	}

	public Cart createCartTravelPlaner(TravelPlanner travelPlanner) {
		Cart cart = new Cart();
		cart.setTravelPlanner(travelPlanner);
		entityManager.persist(cart);
		return cart;
	}

	public void addItemToCart(Long cartId, Item item) {
		Cart cart = entityManager.find(Cart.class, cartId);
		if (cart != null) {
			item.setCart(cart);
			cart.getItems().add(item);
			entityManager.persist(item);
			entityManager.merge(cart);
		}
	}

	public void removeItemFromCart(Long cartId, Long itemId) {
		Cart cart = entityManager.find(Cart.class, cartId);
		Item item = entityManager.find(Item.class, itemId);
		if (cart != null && item != null) {
			cart.getItems().remove(item);
			entityManager.remove(item);
			entityManager.merge(cart);
		}
	}

	public void clearCart(Long cartId) {
		Cart cart = entityManager.find(Cart.class, cartId);
		if (cart != null) {
			for (Item item : cart.getItems()) {
				entityManager.remove(item);
			}
			cart.getItems().clear();
			entityManager.merge(cart);
		}
	}

	public Cart getCartByCustomerId(Long customerId) {
		return entityManager.createQuery("SELECT c FROM Cart c WHERE c.customer.id = :customerId", Cart.class)
				.setParameter("customerId", customerId).getSingleResult();
	}

	public Cart getCartByPartnerId(Long partnerId) {
		return entityManager.createQuery("SELECT p FROM Cart p WHERE p.partner.id = :partnerId", Cart.class)
				.setParameter("partnerId", partnerId).getSingleResult();
	}

	public Cart getCartByTravelPlannerId(Long travelPlannerId) {
		return entityManager.createQuery("SELECT t FROM Cart t WHERE t.travelPlanner.id = :travelPlannerId", Cart.class)
				.setParameter("travelPlannerId", travelPlannerId).getSingleResult();
	}

}