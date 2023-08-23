package travelLinker.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import travelLinker.entity.RoleUser;

public class SessionUtils {
	public SessionUtils() {}

	// Récupère la session HTTP actuelle
	public static HttpSession getSession() {
		HttpSession session;
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Récupère la requête HTTP actuelle
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	// Écrit des informations dans la session
	public static void writeInSession(Long accountId, String email, RoleUser role, String lastName, String firstName
			) {
		getSession().setAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID, accountId);
		getSession().setAttribute("email", email);
		getSession().setAttribute("role", role);
		getSession().setAttribute("lastName", lastName);
		getSession().setAttribute("firstName", firstName);

	}
	public static void writeInSessionTP(String phoneNumber, String siret, String companyName) {
		getSession().setAttribute("siret", siret);
		getSession().setAttribute("companyName", companyName);
		getSession().setAttribute("phoneNumber", phoneNumber);
	
	}



	// Récupère l'email de l'utilisateur à partir de la session
	public static String getUserEmail() {
		HttpSession session = getSession();
		return session.getAttribute("email").toString();
	}

	// Récupère l'ID de l'utilisateur connecté à partir de la session
	public static Long getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (Long) session.getAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID);
		else
			return null;
	}

	public static String getUserFirstName() {
		HttpSession session = getSession();
		return (String) session.getAttribute("firstName");
	}

	public static String getUserLastName() {
		HttpSession session = getSession();
		return (String) session.getAttribute("lastName");
	}

	public static String getUserAddress() {
		HttpSession session = getSession();
		return (String) session.getAttribute("address");
	}
	
	public static String getUserPhone() {
		HttpSession session = getSession();
		return (String) session.getAttribute("phoneNumber");
	}
	
	public static String getUserSiret() {
		HttpSession session = getSession();
		return (String) session.getAttribute("siret");
	}
	public static String getUserCompany() {
		HttpSession session = getSession();
		return (String) session.getAttribute("companyName");
	}

}