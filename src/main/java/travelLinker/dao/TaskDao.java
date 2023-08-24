package travelLinker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Accomodation;
import travelLinker.entity.Account;
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
	    	Account account = SessionUtils.getAccount();
	    	Long accountId = account.getId();
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
	    Account account = SessionUtils.getAccount();
	    Long accountId = account.getId();
	    
	    List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t WHERE t.accountId = :accountId", Task.class)
	                                   .setParameter("accountId", accountId)
	                                   .getResultList();
	    
	    if (tasks == null) {
	        tasks = new ArrayList<>(); // Retourner une liste vide au lieu de null
	    }
	    
	    return tasks;
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
