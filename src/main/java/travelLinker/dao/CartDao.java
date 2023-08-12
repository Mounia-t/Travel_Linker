package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CartDao {

	@PersistenceContext
	private EntityManager entityManager;

//	public Long CreateCart()

}