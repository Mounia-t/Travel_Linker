package travelLinker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import travelLinker.dao.AccountDao;
import travelLinker.entity.Account;
import travelLinker.entity.Partner;
import travelLinker.entity.RoleUser;
import travelLinker.entity.TravelPlanner;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
@SessionScoped
public class AccountControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private AccountDao accountDao;

	private Part imageFile;
	private List<Partner> partners;
	private List<Partner> filteredPartners;
	private String searchKeyword;

	public void addPartner() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		accountDao.createPartner(accountVM, externalContext);
		accountVM = new AccountViewModel();
	}

	public void addCustomer() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		accountDao.createCustomer(accountVM, externalContext);
		accountVM = new AccountViewModel();
	}

	public String addTravelP() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		TravelPlanner createdTravelPlanner = accountDao.createTravelPlanner(accountVM, externalContext);
		accountVM = new AccountViewModel(); // Clear the accountVM
		return null; // Return null to indicate that navigation is handled externally
	}

//	public String addTravelP() {
//		try {
//
//			TravelPlanner createdTravelPlanner = accountDao.createTravelPlanner(accountVM);
//
//			if (createdTravelPlanner != null && createdTravelPlanner.getAccount() != null) {
//				HttpSession session = SessionUtils.getSession();
//				session.setAttribute("loggedInUser", createdTravelPlanner.getAccount());
//				session.setAttribute("currentTravelPlanner", createdTravelPlanner);
//
//				loggedIn = true;
//
//				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//				externalContext.redirect("SubscriptionTP.xhtml");
//			} else {
//				System.out.println("Error: TravelPlanner  not be created.");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//---------------------------------------------------	
	public void deleteAccount() {
		Account account = SessionUtils.getAccount();
		// Vérifier si l'utilisateur est connecté (authentifié)
		Long accountId = account.getId();
		if (accountId != null) {
			// On appelle la méthode de suppression dans le DAO pour supprimer le compte
			accountDao.delete(accountId);
		} else {
			System.out.println("L'utilisateur n'est pas connecté.");
		}
	}

//----------------------------------------------------------	
	public int countTotalPartners() {
		return accountDao.getTotalPartnersCount();

	}

	public List<Partner> getPartners() {
		return accountDao.displayPartners();
	}

	public void updateAccount() {
		Account account = SessionUtils.getAccount();
		// Vérifier si l'utilisateur est connecté (authentifié)
		Long accountId = account.getId();

		if (accountId != null) {
			// Créer un nouvel objet AccountBean avec les valeurs mises à jour
			Account updatedAccount = new Account();
			updatedAccount.setId(accountId);
			updatedAccount.setFirstName(accountVM.getFirstName());
			updatedAccount.setLastName(accountVM.getLastName());
			updatedAccount.setEmail(accountVM.getEmail());
			updatedAccount.setPassword(accountVM.getPassword());
			updatedAccount.setRole(accountVM.getRole());
			updatedAccount.setImagePath(accountVM.getImagePath());

			// Appeler la méthode de mise à jour dans le DAO pour mettre à jour le compte
			accountDao.update(updatedAccount);
		} else {
			System.out.println("L'utilisateur n'est pas connecté.");
		}
	}

	public List<Partner> getNewPartner() {
		return accountDao.getLatestRegisteredPartners(4);

	}

	public List<TravelPlanner> getNewTravelPlanner() {
		return accountDao.getLatestRegisteredTravelPlanners(4);
	}

//--------------------------------------------------------

	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

//----------------------------------------------------------

	public List<Partner> getFilteredPartners() {
		return filteredPartners;
	}

	public void setFilteredPartners(List<Partner> filteredPartners) {
		this.filteredPartners = filteredPartners;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public void searchPartners() {
		filteredPartners = new ArrayList<>();
		for (Partner partner : partners) {
			if (partner.getFirstName().toLowerCase().contains(searchKeyword.toLowerCase())) {
				filteredPartners.add(partner);
			}
		}
	}

	/*
	 * public void uploadProfileImage(FileUploadEvent event) { Part newProfileImage
	 * = (Part) event.getFile();
	 * 
	 * if (newProfileImage != null) { try { // Chemin où vous souhaitez stocker les
	 * images de profil String uploadPath =
	 * "Travel_Linker\\src\\main\\webapp\\images";
	 * 
	 * // Générer un nom de fichier unique pour éviter les conflits String fileName
	 * = System.currentTimeMillis() + "_" + newProfileImage.getSubmittedFileName();
	 * 
	 * // Chemin complet du fichier de sortie Path outputPath = new File(uploadPath,
	 * fileName).toPath();
	 * 
	 * // Copier le fichier téléchargé vers le chemin de sortie try (InputStream
	 * inputStream = newProfileImage.getInputStream()) { Files.copy(inputStream,
	 * outputPath, StandardCopyOption.REPLACE_EXISTING); }
	 * 
	 * // Mettre à jour la référence de l'image de profil dans votre modèle
	 * utilisateur Account account = accountDao.loadAccountFromDataSource(); //
	 * Charge le profil utilisateur depuis la source de données
	 * account.setImagePath(fileName); // Met à jour le chemin de l'image
	 * 
	 * // Enregistrer les modifications dans la base de données
	 * accountDao.update(account); // Met à jour le profil utilisateur dans la base
	 * de données
	 * 
	 * // Afficher un message de succès
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO,
	 * "Photo de profil mise à jour avec succès", null));
	 * 
	 * } catch (IOException e) { // Gérer les erreurs e.printStackTrace();
	 * 
	 * // Afficher un message d'erreur
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "Une erreur s'est produite lors de la mise à jour de la photo de profil",
	 * null)); } }
	 * 
	 * }
	 */
	public boolean isUserTravelPlanner(Long userId) {
		RoleUser userRole = accountDao.getUserRoleById(userId);
		if (userRole == null) {
			// Le rôle de l'utilisateur n'a pas été trouvé
			return false;
		}

		return userRole == RoleUser.TravelPlanner;

	}

}