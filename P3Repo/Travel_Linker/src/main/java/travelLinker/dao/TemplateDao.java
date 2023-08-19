package travelLinker.dao;

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
    
}
