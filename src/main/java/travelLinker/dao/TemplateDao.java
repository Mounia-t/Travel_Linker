package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Template;

@Stateless
public class TemplateDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void CreateTemplate(Template template) {
		entityManager.persist(template);
	}

	public Template findById(Long id) {
		return entityManager.find(Template.class, id);
	}

	public void update(Template template) {
		template = entityManager.merge(template);
		entityManager.flush();

	}
}
