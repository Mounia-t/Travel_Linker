package travelLinker.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import travelLinker.dao.AccomodationDao;
import travelLinker.viewModel.ServiceViewModel;

@ManagedBean
public class AccomodationControllerBean {

	@Inject
	private AccomodationDao accomodationDao;

	private ServiceViewModel accomodationVm = new ServiceViewModel();

	public void createAccomodation() {
		Long id = accomodationDao.createAccomodation(accomodationVm);
		System.out.println("Accomodation created with id : " + id);
		clear();
	}

	public void clear() {
		accomodationVm = new ServiceViewModel();
	}

	public ServiceViewModel getAccomodationVm() {
		return accomodationVm;
	}

	public void setAccomodationVm(ServiceViewModel accomodationVm) {
		this.accomodationVm = accomodationVm;
	}

}
