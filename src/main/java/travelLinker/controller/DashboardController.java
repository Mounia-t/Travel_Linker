package travelLinker.controller;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import travelLinker.utils.SessionUtils;

@ManagedBean
@Stateful
public class DashboardController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String section="mainDashboard";


	public void updateLastMainSection(String divName) {
		section=divName;
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("section", section);		
	}

	public String getSection() {
		HttpSession session = SessionUtils.getSession();
		section=(String) session.getAttribute("section");
		if(section==null) {
		section="mainDashboard";
		}
		return section;
		
	}


	
}
