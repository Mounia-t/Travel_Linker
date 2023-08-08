package travelLinker.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import travelLinker.dao.InsuranceDao;
import travelLinker.viewModel.InsuranceViewModel;

@ManagedBean
public class InsuranceController {
	
	@Inject
	private InsuranceDao insuranceDao;
	private InsuranceViewModel insuranceVM = new InsuranceViewModel();
	
	public void addInsurance() {
		Long id = insuranceDao.insert(insuranceVM);
		System.out.println("Insurance created with id : " + id);
		clear();
	}
	
	public void clear() {
		insuranceVM= new InsuranceViewModel();
	}

	public InsuranceDao getInsuranceDao() {
		return insuranceDao;
	}

	public InsuranceViewModel getInsuranceVM() {
		return insuranceVM;
	}

	public void setInsuranceDao(InsuranceDao insuranceDao) {
		this.insuranceDao = insuranceDao;
	}

	public void setInsuranceVM(InsuranceViewModel insuranceVM) {
		this.insuranceVM = insuranceVM;
	}

	
}
