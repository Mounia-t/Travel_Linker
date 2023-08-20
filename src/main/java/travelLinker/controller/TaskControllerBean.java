package travelLinker.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import travelLinker.dao.TaskDao;
import travelLinker.entity.Task;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
public class TaskControllerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountViewModel accVM = new AccountViewModel();
	@Inject
	private TaskDao taskDao;
	
	
	
	public TaskControllerBean() {
		
	}

	public void addTask() {
		taskDao.insert(accVM);
		AccountViewModel accVM = new AccountViewModel();
	}
	
	public List<Task> getAllTasks(Long accountId){
		return taskDao.getAllTasks(accountId);
		
	}

	public AccountViewModel getAccVM() {
		return accVM;
	}

	public void setAccVM(AccountViewModel accVM) {
		this.accVM = accVM;
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
}
