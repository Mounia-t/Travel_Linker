package travelLinker.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import travelLinker.entity.RoleUser;


public class SessionUtils {
	
	
	
	public static HttpSession getSession() {
		HttpSession session;
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}
		
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
	public static void writeInSession(Long accountId, String email, RoleUser role) {
	
		getSession().setAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID, accountId);
		getSession().setAttribute("email", email);
		
	}

	public static String getUserEmail() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("email").toString();
	}


		public static Long getUserId() {
		    HttpSession session = getSession();
		    if (session != null)
		        return (Long) session.getAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID);
		    else
		        return null;
		}
		
		public void resetSessionAttributes() {
		    // Si on veut accèder à la session http
		            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		            session.invalidate();
		}
}

