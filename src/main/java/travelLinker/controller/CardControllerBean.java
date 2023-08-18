package travelLinker.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import travelLinker.dao.JourneyDao;
import travelLinker.viewModel.JourneyViewModel;

@ManagedBean
@SessionScoped
public class CardControllerBean {

	@Inject
	private JourneyDao journeyDao;

	    private static final long serialVersionUID = 1L;

	    // Ajoutez des propriétés et des méthodes pour récupérer les données du voyage
	    
	    public void fetchJourneyData(JourneyViewModel journeyVM) {
	        country = journeyVM.getCountry();
	        location = journeyVM.getLocation();
	        description = "Découvrez la magie intemporelle de " + country + ", où l'histoire ancienne se mêle harmonieusement aux paysages à couper le souffle.";
	    }

	}
}
