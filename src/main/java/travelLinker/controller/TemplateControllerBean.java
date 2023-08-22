package travelLinker.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.AccountDao;
import travelLinker.dao.TemplateDao;
import travelLinker.entity.Template;
import travelLinker.entity.TravelPlanner;

@ManagedBean
@SessionScoped
public class TemplateControllerBean {

	@Inject
	private TemplateDao templateDao;

	@Inject
	private AccountDao accountDao;

	private Template selectedTemplate = new Template();

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

//	public String selectTemplate() {
//
//		TravelPlanner currentTravelPlanner = sessionBean.getCurrentTravelPlanner();
//		if (currentTravelPlanner != null) {
//			currentTravelPlanner.setTemplate(selectedTemplate);
//
//			accountDao.updateTravelPlanner(currentTravelPlanner);
//		}
//		return "dashboardTP?faces-redirect=true";
//	}

	public String selectTemplate(Template template) {
		this.selectedTemplate = template;
		TravelPlanner currentTravelPlanner = sessionBean.getCurrentTravelPlanner();
		if (currentTravelPlanner != null) {
			currentTravelPlanner.setTemplate(template);
			accountDao.updateTravelPlanner(currentTravelPlanner);
		}
		return "dashboardTP?faces-redirect=true";
	}

	public String loadTemplate(Long templateId) {
		selectedTemplate = templateDao.getTemplateById(templateId);
		return "dashboardTP.xhtml";
	}

	public String addTemplate() {
		templateDao.createTemplate(selectedTemplate);
		selectedTemplate = new Template();
		return "templateListPage?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		System.out.println("Init method called");
		if (selectedTemplate == null) {
			selectedTemplate = new Template();
			selectedTemplate.setBackgroundColor("#8a6451");
		}
	}

	public void updateBackgroundColor() {
		System.out.println("noluyor amk");
		TravelPlanner currentTravelPlanner = sessionBean.getCurrentTravelPlanner();
		if (currentTravelPlanner != null) {
			currentTravelPlanner.setTemplate(selectedTemplate);
			System.out.println("selecgtionné" + selectedTemplate + currentTravelPlanner);
			accountDao.updateTravelPlanner(currentTravelPlanner);
		}
	}

	public List<Template> getAllTemplates() {
		return templateDao.getAllTemplates();
	}

	public TemplateDao getTemplateDao() {
		return templateDao;
	}

	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	public Template getSelectedTemplate() {
		return selectedTemplate;
	}

	public void setSelectedTemplate(Template selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
