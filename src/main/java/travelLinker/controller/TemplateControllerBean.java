package travelLinker.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.AccountDao;
import travelLinker.dao.LoginDao;
import travelLinker.dao.TemplateDao;
import travelLinker.entity.Template;
import travelLinker.entity.TravelPlanner;
import travelLinker.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class TemplateControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long templateId;
	private Template template;

	@Inject
	private TemplateDao templateDao;

	@Inject
	private LoginDao loginDao;

	@Inject
	private AccountDao accountDao;

	private String selectedColor;

	private List<ColorItem> predefinedColorItems = Arrays.asList(new ColorItem("Rouge", "#FF5733"),
			new ColorItem("Vert", "#33FF57"), new ColorItem("Coral", "#ff7f50"), new ColorItem("Bleu", "#3357FF"),
			new ColorItem("Rose", "#FF33F6"), new ColorItem("Orange", "#FF8C33"));


	
	public String updateBackgroundColor() {
	    System.out.println("Mise à jour de la couleur d'arrière-plan: " + selectedColor);

	    String userEmail = SessionUtils.getAccount().getEmail();
	    
	    // Vérifier si l'utilisateur est connecté en tant que travel planner
	    if (userEmail != null) {
	        TravelPlanner tp = loginDao.findTravelPlanner(userEmail);

	        if (tp == null) {
	            System.err.println("Erreur : Aucun TravelPlanner trouvé pour l'e-mail " + userEmail);
	            return null;
	        }

	        if (tp.getTemplate() == null) {
	            Template newTemplate = new Template();
	            newTemplate.setBackgroundColor(selectedColor);
	            tp.setTemplate(newTemplate);
	            templateDao.CreateTemplate(newTemplate);
	        } else {
	            tp.getTemplate().setBackgroundColor(selectedColor);
	            templateDao.update(tp.getTemplate());
	        }

	        accountDao.updateTravelPlanner(tp);
	        this.template = tp.getTemplate();
	        System.out.println("Fin de la méthode updateBackgroundColor");

	        return "HomeTP?faces-redirect=true";
	    }
	    
	    // Si l'utilisateur n'est pas connecté, exécuter la logique pour mettre à jour la personnalisation
	    // pour les autres utilisateurs sans rediriger
	    Template publicTemplate = new Template();
	    publicTemplate.setBackgroundColor(selectedColor);
	    this.template = publicTemplate;
	    System.out.println("Mise à jour de la personnalisation pour les utilisateurs publics");
	    
	    return null;  // Pas de redirection
	}


	public List<ColorItem> getPredefinedColorItems() {
		return predefinedColorItems;
	}

	public void loadTemplate() {
		this.template = templateDao.findById(templateId);
	}

	public void saveTemplate() {
		templateDao.update(template);
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Template getTemplate() {
		if (template == null) {
			template = templateDao.loadTemplateForCurrentUser();
		}
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}

	public static class ColorItem {
		private String label;
		private String value;

		public ColorItem(String label, String value) {
			this.label = label;
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ColorItem [label=");
			builder.append(label);
			builder.append(", value=");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}

	}

}
