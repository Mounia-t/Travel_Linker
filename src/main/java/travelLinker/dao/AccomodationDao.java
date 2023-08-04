package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Accomodation;
import travelLinker.viewModel.ServiceViewModel;

@Stateless
public class AccomodationDao {

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

	public void deleteAccomodation(Accomodation accomodation) {
		entityManager.remove(accomodation);
	}

	public Accomodation accomodationById(Long id) {
		return entityManager.find(Accomodation.class, id);
	}

}
