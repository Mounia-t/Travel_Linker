package travelLinker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Template;

@Stateless
public class TemplateDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Template createTemplate(Template template) {
		entityManager.persist(template);
		return template;
	}

	public Template getTemplateById(Long id) {
		return entityManager.find(Template.class, id);
	}

	public void updateTemplate(Template template) {
		entityManager.merge(template);
	}

	public List<Template> getAllTemplates() {
		try {
			return entityManager.createQuery("SELECT t FROM Template t", Template.class).getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
