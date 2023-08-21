package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Accomodation;
import travelLinker.entity.Journey;
import travelLinker.entity.Task;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;


@Stateless
public class TaskDao {
	

	@PersistenceContext
	private EntityManager entityManager;


	public Long insert(AccountViewModel accVM) {
	    try {
	    	Task task = new Task();
	    	Long accountId = SessionUtils.getUserId();
	    	task.setTaskContent(accVM.getTaskContent());
	    	task.setAccountId(accountId);
	        entityManager.persist(task);
	        entityManager.flush();
	        return task.getId();
	    
	    }catch (Exception e) {
	        e.printStackTrace(); // Handle exceptions appropriately
	        return null;
	    }
		
}
	public List<Task> getAllTasks() {
		Long accountId = SessionUtils.getUserId();
	    return entityManager.createQuery("SELECT t FROM Task t WHERE t.accountId = :accountId", Task.class)
	            .setParameter("accountId", accountId)
	            .getResultList();
	}
	 public void deleteTask(Long id) {
				Task task = entityManager.find(Task.class, id);
				if (task != null) {
					entityManager.remove(task);
				}
			}
	 

	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}
