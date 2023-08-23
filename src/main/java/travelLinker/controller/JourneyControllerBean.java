package travelLinker.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import travelLinker.dao.JourneyDao;
import travelLinker.dao.ServiceDao;
import travelLinker.entity.Journey;
import travelLinker.entity.Service;
import travelLinker.viewModel.JourneyViewModel;

	@ManagedBean
	public class JourneyControllerBean  implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JourneyViewModel journeyVM = new JourneyViewModel();
		
		@Inject
		private JourneyDao journeyDao;
		
		@Inject
		private DashboardController dashController;
		@Inject
		private ServiceDao serviceDao;
		
		@Lob
		@Basic(fetch = FetchType.LAZY)
		@Column(columnDefinition = "BLOB")
		private Part imageFile;
		
		private List<Service> filteredServices;

		
		public void addJourney() {
		    try {
		        if (imageFile != null) {
		            // Obtenez le chemin absolu vers le répertoire de déploiement de l'application
		            String deploymentPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		            
		            // Chemin relatif vers le répertoire d'images
		            String relativeImagePath = "images/";

		            String fileName = "journey_" + System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(imageFile.getSubmittedFileName()) + "."
		                    + FilenameUtils.getExtension(imageFile.getSubmittedFileName());

		            // Chemin complet du fichier
		            String filePath = Paths.get(deploymentPath, relativeImagePath, fileName).toString();
		            System.out.println("Deployment Path: " + deploymentPath);
		            System.out.println("Relative Image Path: " + relativeImagePath);
		            System.out.println("File Name: " + fileName);
		            System.out.println("File Path: " + filePath);
		            try (InputStream input = imageFile.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
		                IOUtils.copy(input, output);
		            }

		            journeyVM.setImagePath(relativeImagePath + fileName);
		        }
		        

		        // Appel à la méthode de la couche de persistance pour ajouter le voyage
		        Long id = journeyDao.insert(journeyVM);

		        System.out.println("Journey created with id : " + id);
		        clear();
		        dashController.updateLastMainSection("mainManagedResa");
		    } catch (IOException e) {
		        e.printStackTrace(); // Gérez l'exception selon vos besoins
		    }
		}
	                
		public void clear() {
			journeyVM = new JourneyViewModel();
		}
		
		public void deleteJourney (Long id) {
			journeyDao.deleteJourney(id);
			System.out.println("Journey deleted with id " + id);
		}

		public JourneyDao getJourneyDao() {
			return journeyDao;
		}

		public JourneyViewModel getJourneyVM() {
			return journeyVM;
		}

		public void setJourneyDao(JourneyDao journeyDao) {
			this.journeyDao = journeyDao;
		}

		public void setJourneyVM(JourneyViewModel journeyVM) {
			this.journeyVM = journeyVM;
		}



		public Part getImageFile() {
			return imageFile;
		}

		public void setImageFile(Part imageFile) {
			this.imageFile = imageFile;
		}
		
	
		public DashboardController getDashController() {
			return dashController;
		}

		public void setDashController(DashboardController dashController) {
			this.dashController = dashController;
		}

		public ServiceDao getServiceDao() {
			return serviceDao;
		}

		public void setServiceDao(ServiceDao serviceDao) {
			this.serviceDao = serviceDao;
		}

		public List<Journey> getAllJourneys(){
			return journeyDao.getAllJourneys();
			
		}
		public List<Journey> displayTravelPlaJournyes(){
			return journeyDao.getTravelPlannerJourneys();
		}
		public void filterServicesByCountry() {
		    String selectedCountry = journeyVM.getCountry(); // Récupérer le pays sélectionné
		    if (selectedCountry != null && !selectedCountry.isEmpty()) {
		        filteredServices = serviceDao.displayFiltredServices(selectedCountry);
		    }
			
		}
	}


