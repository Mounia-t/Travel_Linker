package travelLinker.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.TemplateDao;
import travelLinker.entity.Template;

@ManagedBean
@SessionScoped
public class TemplateControllerBean {

	@Inject
    private TemplateDao templateDao;

    private Template selectedTemplate;

    public String loadTemplate(Long templateId) {
        selectedTemplate = templateDao.getTemplateById(templateId);
        return "userHomePage.xhtml";
    }
    
    public String saveTemplate() {
        templateDao.createTemplate(selectedTemplate);
        return "userHomePage.xhtml";
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
}
