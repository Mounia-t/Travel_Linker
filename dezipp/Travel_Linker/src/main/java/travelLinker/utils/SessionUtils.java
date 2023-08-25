package travelLinker.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import travelLinker.dao.AccountDao;
import travelLinker.entity.Account;
import travelLinker.entity.Partner;

import travelLinker.entity.RoleUser;
import travelLinker.entity.TravelPlanner;

public class SessionUtils {
	public SessionUtils() {}

	private static final String USER_KEY = "loggedInUser";
	private static final String TRAVEL_PLANNER="loggedInTP";
	private static final String PARTNER="loggedInPartner";

    public static HttpSession getSession() {
    	HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       System.out.println("Ma session " + session);
    	return session;
    }

    // Storing user object in session
    public static void setAccount(Account user) {
        getSession().setAttribute(USER_KEY, user);
    }

    // Retrieving user object from session
    public static Account getAccount() {
    Account account=(Account) getSession().getAttribute(USER_KEY);
    System.out.println(account);
        return account;
    }
    
    public static void setTravelPlanner(TravelPlanner travelP) {
    	getSession().setAttribute(TRAVEL_PLANNER, travelP);
    }
    public static TravelPlanner getTravelPlanner() {
		return  (TravelPlanner)getSession().getAttribute(TRAVEL_PLANNER);
	}
    
    public static void setPartner(TravelPlanner Partner) {
    	getSession().setAttribute(PARTNER, Partner);
    }
    public static Partner getPartner() {
		return  (Partner)getSession().getAttribute(PARTNER);
	}

}