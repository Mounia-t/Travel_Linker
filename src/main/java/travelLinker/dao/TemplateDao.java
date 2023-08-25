package travelLinker.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Template;
import travelLinker.entity.TravelPlanner;
import travelLinker.utils.SessionUtils;

@Stateless
public class TemplateDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private LoginDao loginDao;

	public void CreateTemplate(Template template) {
		entityManager.persist(template);
	}

	public Template loadTemplateForCurrentUser() {
		String currentUserEmail = SessionUtils.getAccount().getEmail();
		TravelPlanner tp = loginDao.findTravelPlanner(currentUserEmail);
		if (tp != null && tp.getTemplate() != null) {
			return tp.getTemplate();
		} else {
			return null;
		}
	}

	public Template findById(Long id) {
		return entityManager.find(Template.class, id);
	}

	public void update(Template template) {
		template = entityManager.merge(template);
		entityManager.flush();

	}
}
