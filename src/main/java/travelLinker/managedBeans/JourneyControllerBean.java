package travelLinker.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import travelLinker.dao.JourneyDao;
import travelLinker.viewModel.JourneyViewModel;

@ManagedBean
public class JourneyControllerBean{
	
	@Inject
	private JourneyDao journeyDao;
	private JourneyViewModel journeyVM = new JourneyViewModel();

	public void addJourney() {

		Long id= journeyDao.insert(journeyVM);
		System.out.println("Journey created with id : " + id);
		clear();
	}
	
	public void clear() {
		journeyVM = new JourneyViewModel();
	}

	public JourneyDao getJourneyDao() {
		return journeyDao;
	}

	public JourneyViewModel getJourneyVM() {
		return journeyVM;
	}

	public void setJourneyDao(JourneyDao journeyDao) {
		this.journeyDao = journeyDao;
	}

	public void setJourneyVM(JourneyViewModel journeyVM) {
		this.journeyVM = journeyVM;
	}
	


}
