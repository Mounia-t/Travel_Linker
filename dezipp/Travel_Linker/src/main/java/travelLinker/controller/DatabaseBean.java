//package travelLinker.controller;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//
//import travelLinker.dao.TemplateDao;
//import travelLinker.entity.Template;
//
//@Singleton
//@Startup
//public class DatabaseBean {
//
//	@EJB
//	private TemplateDao templateDao;
//
//	@PostConstruct
//	public void initialize() {
//		List<Template> existingTemplates = templateDao.getAllTemplates();
//
//		if (existingTemplates == null || existingTemplates.isEmpty()) {
//			createDefaultTemplates();
//		}
//	}
//
//	private void createDefaultTemplates() {
//		Template blueTemplate = new Template();
//		blueTemplate.setNom("Bleu clair");
//		blueTemplate.setBackgroundColor("#ADD8E6");
//		templateDao.createTemplate(blueTemplate);
//
//		Template greenTemplate = new Template();
//		greenTemplate.setNom("Vert clair");
//		greenTemplate.setBackgroundColor("#90EE90");
//		templateDao.createTemplate(greenTemplate);
//
//		Template pinkTemplate = new Template();
//		pinkTemplate.setNom("Rose");
//		pinkTemplate.setBackgroundColor("#FFC0CB");
//		templateDao.createTemplate(pinkTemplate);
//	}
//}
