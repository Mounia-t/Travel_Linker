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

import travelLinker.dao.LoginDao;
import travelLinker.entity.Account;
import travelLinker.entity.RoleUser;
import travelLinker.entity.Template;
import travelLinker.entity.TravelPlanner;
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
	private boolean loggedIn=false;

	@Inject
	private TemplateControllerBean templateControllerBean;
	private boolean loggedIn = false;

	private Account account;

	public LoginControllerBean() {
	}

	public void validateAccount() {
		boolean isValid = loginDao.validate(accountVM);
		Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(),
				accountVM.getPassword());

		// Si les informations de connexion sont valides
		if (isValid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("loggedInUser", account);
			loggedIn = true;
			// Récupérer l'URL de redirection
			String redirectionUrl = redirectionDashboard();

			// Recuperer les données de la session

			// SessionUtils.writeInSessionTP(tp.getSiret(), tp.getPhoneNumber(),
			// tp.getCompanyName());
			;
			// TODO Ajouter travel planner dans la session

			if (account.getRole() == RoleUser.TravelPlanner) {
				String userEmail = SessionUtils.getAccount().getEmail();
				TravelPlanner tp = loginDao.findTravelPlanner(userEmail);
				Template userTemplate = tp.getTemplate();
				templateControllerBean.setTemplate(userTemplate);
				System.out.println("validate account la template est " + userTemplate + " travel planner est " + tp);
			}

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

	public String redirectionDashboard() {
		Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(),
				accountVM.getPassword());

		System.out.println(account.getRole());

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
			redirectionUrl = "SubscriptionTP.xhtml"; // Remplacez "default-dashboard.xhtml" par l'URL de la page de
														// tableau
			// de bord par défaut
		}
		return redirectionUrl;
	}

	// Méthode pour se déconnecter, sans invalider la session entière
	public String logout() {
		account = SessionUtils.getAccount();
		System.out.println("logout avec account is " + account);

		if (account.getRole() == RoleUser.TravelPlanner) {
			String userEmail = SessionUtils.getAccount().getEmail();
			System.out.println(" logout avec user e mail " + userEmail);
			TravelPlanner tp = loginDao.findTravelPlanner(userEmail);
			System.out.println("logout avec " + tp + " its ok");
			HttpSession session = SessionUtils.getSession();
			session.removeAttribute("userId");
			session.removeAttribute("userEmail");
			session.removeAttribute("userRole");
		}

		loginDao.logout();
		return "signIn"; // Rediriger vers la page de connexion
	}

	public Account getConnectedAccount() {
		HttpSession session = SessionUtils.getSession();
		System.out.println("Session : " + session);
		Account account = SessionUtils.getAccount();
		System.out.println(account);
		return account;
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

	public boolean isLoggedIn() {
		Account account = SessionUtils.getAccount();
		loggedIn = (account != null);
		System.out.println(loggedIn);
		return loggedIn;
	}

	public String redirectToLogin() {
		return "signIn.xhtm";
	}
}
