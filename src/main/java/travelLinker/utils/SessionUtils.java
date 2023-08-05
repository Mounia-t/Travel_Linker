package travelLinker.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import travelLinker.entity.RoleUser;

public class SessionUtils {

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
    public static void writeInSession(Long accountId, String email, RoleUser role) {
        getSession().setAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID, accountId);
        getSession().setAttribute("email", email);
        getSession().setAttribute("role", role);
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

    // Méthode non statique pour lire une valeur à partir de la session
    // et affiche cette valeur (ou un message d'absence) dans la console
    public static void readFromSession() {
        HttpSession session = getSession();
        Long id = (Long) session.getAttribute(SessionAttributesUtils.CONNECTED_ACCOUNT_ID);
        if (id != null) {
            System.out.println(id);
        } else {
            System.out.println("Pas d'id dans la session");
        }
    }
}