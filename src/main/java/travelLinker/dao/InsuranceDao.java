package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelLinker.entity.Insurance;

import travelLinker.viewModel.InsuranceViewModel;
@Stateless
public class InsuranceDao {

		@PersistenceContext
		private EntityManager entityManager;

		public Long insert(InsuranceViewModel insuranceVM) {
			Insurance insurancebean = new Insurance();
			insurancebean.setType(insuranceVM.getType());
			insurancebean.setStartDate(insuranceVM.getStartDate());
			insurancebean.setEndDate(insuranceVM.getEndDate());
			insurancebean.setDescription(insuranceVM.getDescription());
			insurancebean.setPrice(insuranceVM.getPrice());

			entityManager.persist(insurancebean);
			entityManager.flush();
			return insurancebean.getId();
		}
	}