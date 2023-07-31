package travelLinker.controllerBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import travelLinker.beans.AccountBean;

@ManagedBean
@ViewScoped
public class AccountControllerBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "Welcome to Java EE : your first JSF bean and page !";
	private List<AccountBean>accounts=new ArrayList<>();

	private AccountBean accountBean= new AccountBean();
	public void addAccount(AccountBean accountBean) {
		accounts.add(accountBean);
	}
	
	public AccountBean getAccountBean() {
		return accountBean;
	}
	public void setAccountBean(AccountBean accountBean) {
		this.accountBean = accountBean;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
