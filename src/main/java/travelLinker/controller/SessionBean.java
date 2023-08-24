//package travelLinker.controller;
//
//import java.io.Serializable;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.inject.Inject;
//
//import travelLinker.entity.TravelPlanner;
//
//@ManagedBean
//@SessionScoped
//public class SessionBean implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	private TemplateControllerBean templateController;
//
//	private TravelPlanner currentTravelPlanner;
//
//	public void setCurrentTravelPlanner(TravelPlanner currentTravelPlanner) {
//		System.out.println("setCurrentTravelPlanner est: " + currentTravelPlanner);
//		this.currentTravelPlanner = currentTravelPlanner;
//		templateController.setSelectedTemplate(currentTravelPlanner.getTemplate());
//	}
//
//	public TravelPlanner getCurrentTravelPlanner() {
//		return currentTravelPlanner;
//	}
//
//}
