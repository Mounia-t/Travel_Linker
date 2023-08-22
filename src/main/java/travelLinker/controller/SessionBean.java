package travelLinker.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.entity.TravelPlanner;

@ManagedBean
@SessionScoped
public class SessionBean {

	@Inject
	private TemplateControllerBean templateController;

	private TravelPlanner currentTravelPlanner;

	public void setCurrentTravelPlanner(TravelPlanner currentTravelPlanner) {
		this.currentTravelPlanner = currentTravelPlanner;
		templateController.setSelectedTemplate(currentTravelPlanner.getTemplate());
	}

	public TravelPlanner getCurrentTravelPlanner() {
		return currentTravelPlanner;
	}

}
