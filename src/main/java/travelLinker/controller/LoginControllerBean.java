package travelLinker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import travelLinker.dao.LoginDao;
import travelLinker.entity.AccountBean;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean

//Serializable : les instances de cette classe peuvent être sérialisées 
//(converties en un flux d'octets) pour être stockées dans un flux, tel qu'une session HTTP.
public class LoginControllerBean implements Serializable {

	//Attributs
	private static final long serialVersionUID = 1L;
	//je déclare un entityManger pour interagir avec la BDD
	
	private EntityManager entityManager;
	private AccountViewModel accountVM=new AccountViewModel();
	private List<AccountBean> accounts = new ArrayList<>();
	 @Inject
	    private LoginDao loginDao;

	    public LoginControllerBean() {
	    }

/*	    // Méthode d'initialisation, appelée après l'injection des dépendances
	    @PostConstruct
	    public void init() {
	        loginDao.setEntityManager(entityManager);
	    }
	*/
	// private AccountViewModel accountVM=new AccountViewModel();
	
	    public String validateUsernamePassword() {
	        // Vérifier si le nom d'utilisateur et le mot de passe saisis sont valides
	        boolean valid = loginDao.validate(accountVM.getEmail(), accountVM.getPassword());

	        // Si les informations de connexion sont valides
	        if (valid) {
	            // Obtenir une session HTTP
	            HttpSession session = SessionUtils.getSession();
	            session.setAttribute("email", accountVM);
	            return "index"; // Rediriger
	        } else {
	            FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN,
	                        "Incorrect Username and Password",
	                        "Please enter correct username and Password"));
	            return "login"; // Rediriger vers la page de connexion en cas d'informations de connexion incorrectes
	        }
	    }

	    // Méthode pour se déconnecter, invalider la session
	    public String logout() {
	        HttpSession session = SessionUtils.getSession();
	        session.invalidate();
	        return "login"; // Rediriger vers la page de connexion après la déconnexion
	    }


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public List<AccountBean> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountBean> accounts) {
		this.accounts = accounts;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}


	

}
