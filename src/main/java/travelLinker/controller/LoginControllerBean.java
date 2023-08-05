package travelLinker.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import travelLinker.dao.LoginDao;
import travelLinker.entity.Account;
import travelLinker.entity.RoleUser;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean

//Serializable : les instances de cette classe peuvent être sérialisées 
//(converties en un flux d'octets) pour être stockées dans un flux, tel qu'une session HTTP.
public class LoginControllerBean implements Serializable {

	// Attributs
	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private LoginDao loginDao;

	public LoginControllerBean() {
	}
/*
public String validateUsername() {
		// Vérifier si le nom d'utilisateur et le mot de passe saisis sont valides
		boolean valid = loginDao.validate(accountVM);

		// Si les informations de connexion sont valides
		if (valid) {
			// Obtenir une session HTTP
			SessionUtils.writeInSession(accountVM.getId(), accountVM.getEmail(), accountVM.getRole());
			Account account = loginDao.findAccountByEmailAndPasswor(accountVM, accountVM.getEmail(), accountVM.getPassword());

			RoleUser role = account.getRole();
            Long accountId = account.getId(); 
            
            
			System.out.println(account.getId());
			System.out.println(account.getEmail());
			System.out.println(account.getRole());
			return "index";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					// l'objet FacesMessage utilisé pour afficher des messages d'informations,
					// d'avertissements ou d'erreurs à l'utilisateur dans une appli JSF
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Email ou Mot de pass incorrectes",
							"Merci de saisir les bons identifiants"));
			return "signIn"; // Rediriger vers la page de connexion en cas d'informations de connexion
			
		}
	}*/
	public void validateAccount() {
	    // Vérifier si le nom d'utilisateur et le mot de passe saisis sont valides
	    boolean valid = loginDao.validate(accountVM);

	    // Si les informations de connexion sont valides
	    if (valid) {
	        // Récupérer l'URL de redirection
	        String redirectionUrl = redirectionDashboard();

	        // Effectuer la redirection
	        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            ec.redirect(redirectionUrl);
	        } catch (IOException e) {
	            // Gérer l'exception en cas d'erreur de redirection
	            e.printStackTrace();
	        }
	    } else {
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN, "Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
	        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            ec.redirect("Login.xhtml");
	        } catch (IOException e) {
	            // Gérer l'exception en cas d'erreur de redirection
	            e.printStackTrace();
	        }
	    }
	}

	public String redirectionDashboard() {
	    Account account = loginDao.findAccountByEmailAndPasswor(accountVM, accountVM.getEmail(), accountVM.getPassword());

	    if (account != null) {
	        // Le compte a été trouvé dans la base de données.
	        // Vous pouvez maintenant obtenir le rôle et l'ID à partir du compte.
	        RoleUser role = account.getRole();

	        String redirectionUrl;

	        if (role == RoleUser.Partner) {
	            // Redirection vers le tableau de bord des partenaires
	            redirectionUrl = "DashboardPartner.xhtml"; // Remplacez "dashboard-partner.xhtml" par l'URL du tableau de bord des partenaires
	        } else if (role == RoleUser.Customer) {
	            // Redirection vers le tableau de bord des clients
	            redirectionUrl = "DashboardCustomer.xhtml"; // Remplacez "dashboard-customer.xhtml" par l'URL du tableau de bord des clients
	        } else if (role == RoleUser.TravelPlanner) {
	            // Redirection vers le tableau de bord des clients
	            redirectionUrl = "DashboardTravelPlanner.xhtml"; 
	        } else {
	            // Redirection par défaut (par exemple, si le rôle n'est pas géré)
	            redirectionUrl = "index.xhtml"; // Remplacez "default-dashboard.xhtml" par l'URL de la page de tableau de bord par défaut
	        }

	        return redirectionUrl;
	    } else {
	        // Compte non trouvé dans la base de données
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN, "Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
	        return "Login.xhtml";
	    }
	}


	// Méthode pour se déconnecter, invalider la session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "signIn"; // Rediriger vers la page de connexion
	}
	
	
	public void getId() {
		
		
	}

	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}


	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

}
