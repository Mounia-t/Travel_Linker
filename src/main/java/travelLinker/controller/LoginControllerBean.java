package travelLinker.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.xml.GetterType;

import travelLinker.dao.LoginDao;
import travelLinker.entity.Account;
import travelLinker.entity.RoleUser;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
@SessionScoped

//Serializable : les instances de cette classe peuvent être sérialisées 
//(converties en un flux d'octets) pour être stockées dans un flux, tel qu'une session HTTP.
public class LoginControllerBean implements Serializable {

	// Attributs
	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private LoginDao loginDao;
	private boolean connected;

	public LoginControllerBean() {
	}

	public void validateAccount() {
		// Vérifier si le nom d'utilisateur et le mot de passe saisis sont valides
		boolean valid = loginDao.validate(accountVM);
		Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(),
				accountVM.getPassword());

		// Si les informations de connexion sont valides
		if (valid) {
			connected=true;
			// Récupérer l'URL de redirection
			String redirectionUrl = redirectionDashboard();

			// Recuperer les données de la session
			SessionUtils.writeInSession(account.getId(), account.getEmail(), true, account.getRole(), account.getFirstName(),
					account.getLastName());
	//	SessionUtils.writeInSessionTP(tp.getSiret(), tp.getPhoneNumber(), tp.getCompanyName());
			;

			// Effectuer la redirection
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(redirectionUrl);
			} catch (IOException e) {
				// Gérer l'exception en cas d'erreur de redirection
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect("signIn.xhtml");
			} catch (IOException e) {
				// Gérer l'exception en cas d'erreur de redirection
				e.printStackTrace();
			}
		}
	}
	
	
	public void setConnected(boolean isConnected) {
		this.connected = isConnected;
	}

	public boolean isConnected() {
        HttpSession session = SessionUtils.getSession();
        Boolean connected = (Boolean) session.getAttribute("connected");
        return connected != null && connected;
    }
	 
	public String logout() {
	    // Récupérer la session
	    HttpSession session = SessionUtils.getSession();

	    // Supprimer les informations de session
	    session.invalidate(); // Invalidate the session

	    // Effectuer la redirection vers la page d'accueil après la déconnexion
	    return "signIn.xhtml?faces-redirect=true";
	}
	
	public void redirectToDashboard() throws IOException {
	    String redirectionUrl = redirectionDashboard();
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(redirectionUrl);
	}

	public String redirectionDashboard() {
		Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(),
				accountVM.getPassword());

		if (account != null) {
			// Le compte a été trouvé dans la base de données.
			// Vous pouvez maintenant obtenir le rôle et l'ID à partir du compte.
			RoleUser role = account.getRole();

			String redirectionUrl;

			if (role == RoleUser.Partner) {
				// Redirection vers le tableau de bord des partenaires
				redirectionUrl = "DashboardPartner.xhtml"; // Remplacez "dashboard-partner.xhtml" par l'URL du tableau
															// de bord des partenaires
			} else if (role == RoleUser.Customer) {
				// Redirection vers le tableau de bord des clients
				redirectionUrl = "DashboardCustomer.xhtml"; // Remplacez "dashboard-customer.xhtml" par l'URL du tableau
															// de bord des clients
			} else if (role == RoleUser.TravelPlanner) {

				// Redirection vers le tableau de bord des clients
				redirectionUrl = "dashboardTP.xhtml";
			} else {
				// Redirection par défaut (par exemple, si le rôle n'est pas géré)
				redirectionUrl = "SubscriptionTP.xhtml"; // Remplacez "default-dashboard.xhtml" par l'URL de la page de tableau
												// de bord par défaut
			}
			return redirectionUrl;
		} else {
			// Compte non trouvé dans la base de données
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
			return "signIn.xhtml";
		}
	}



	public String getUserFirstName() {
		return SessionUtils.getUserFirstName();
	}

	public String getUserLastName() {
		return SessionUtils.getUserLastName();
	}

	public String getUserAddress() {
		return SessionUtils.getUserAddress();
	}

	public String getUserEmail() {
		return SessionUtils.getUserEmail();
	}

    public String getUserSiret() {
	        return SessionUtils.getUserSiret();
	    }
    public String getUserPhone() {
		return SessionUtils.getUserPhone();  	
    }
    public String getUserCompany() {
        return SessionUtils.getUserCompany();
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
