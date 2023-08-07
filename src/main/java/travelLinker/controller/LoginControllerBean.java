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

	
	    public String validateUsername() {
	        // Vérifier si le nom d'utilisateur et le mot de passe saisis sont valides
	        boolean valid = loginDao.validate(accountVM);

	        // Si les informations de connexion sont valides
	        if (valid) {
	        //----------------------------------------	
	        	// récupérer l'id par email dans le LoginDao
	        	Long accountId = loginDao.getAccountIdByEmail(accountVM.getEmail());
	          
	            // Stocker l'ID du compte dans le ViewModel
	            accountVM.setId(accountId);
	        //-------------------------------------------	
	        	
	            // Obtenir une session HTTP
	        	SessionUtils.writeInSession(accountVM.getId(), accountVM.getEmail(), accountVM.getRole());
	            return "index"; 
	         
	        } else {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    //l'objet FacesMessage, qui est utilisé pour afficher des messages d'informations, 
	                    //d'avertissements ou d'erreurs à l'utilisateur dans une application JSF 
	                    new FacesMessage(FacesMessage.SEVERITY_WARN,
	                            "Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
	            return "Login"; // Rediriger vers la page de connexion en cas d'informations de connexion incorrectes
	        }
	    }
	    
		// Méthode pour se déconnecter, invalider la session
	    public String logout() {
	        HttpSession session = SessionUtils.getSession();
	        session.invalidate();
	        return "login"; // Rediriger vers la page de connexion après la déconnexion
	    }


	    public AccountViewModel getAccountVM() {
			return accountVM;
		}

		public void setAccountVM(AccountViewModel accountVM) {
			this.accountVM = accountVM;
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
