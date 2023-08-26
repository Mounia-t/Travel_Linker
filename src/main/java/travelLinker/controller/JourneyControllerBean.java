package travelLinker.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.servlet.http.Part;

import travelLinker.dao.JourneyDao;
import travelLinker.entity.Journey;
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

		@Lob
		@Basic(fetch = FetchType.LAZY)
		@Column(columnDefinition = "BLOB")
		private Part imageFile;
		
		private Journey selectedJourney;
		private Journey selectedJourneyForPay;

		public List<Journey> journeys;
		public void addJourney() {
		    try {
		        if (imageFile != null) {
		            // Obtenez le chemin absolu vers le répertoire de déploiement de l'application
		            String deploymentPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

		            // Chemin relatif vers le répertoire d'images (à partir du répertoire "media")
		            String relativeImagePath = "media/images/";

		            String fileName = "journey_" + System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(imageFile.getSubmittedFileName()) + "."
		                    + FilenameUtils.getExtension(imageFile.getSubmittedFileName());

		            // Utilisez Paths pour construire correctement le chemin
		            Path deploymentPathPath = Paths.get(deploymentPath);
		            Path imagePathPath = Paths.get(relativeImagePath, fileName);
		            Path filePath = deploymentPathPath.resolve(imagePathPath);

		            String filePathString = filePath.toString(); // Chemin complet du fichier
		            System.out.println("File Path: " + filePathString);

		            try (InputStream input = imageFile.getInputStream(); OutputStream output = new FileOutputStream(filePathString)) {
		                IOUtils.copy(input, output);
		            }

		            journeyVM.setImagePath(relativeImagePath + fileName);
		        }

		   journeyDao.insert(journeyVM);

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

		public List<Journey> getJourneys() {
			return journeys;
		}

		public void setJourneys(List<Journey> journeys) {
			this.journeys = journeys;
		}
		public List<Journey> getAllJourneys(){
			return journeyDao.getAllJourneys();

		}
		public List<Journey> displayTravelPlaJournyes(){
			return journeyDao.getTravelPlannerJourneys();
		}
		

		public Journey getSelectedJourney() {
			return selectedJourney;
		}

		public void setSelectedJourney(Journey selectedJourney) {
			this.selectedJourney = selectedJourney;
		}

		
		public Journey getSelectedJourneyForPay() {
			return selectedJourneyForPay;
		}

		public void setSelectedJourneyForPay(Journey selectedJourneyForPay) {
			this.selectedJourneyForPay = selectedJourneyForPay;
		}

		public String reserveAndRedirect(Long journeyId) {
	        // Utilisez journeyId pour charger le voyage sélectionné
	        selectedJourney = journeyDao.findJourneyById(journeyId);
	        System.out.println("ma journey "+ selectedJourney);
	        return "produit.xhtml";

	}
		public String reserveAndPayment(Long journeyId) {
		    // Utilisez journeyId pour charger le voyage sélectionné
		    selectedJourney = journeyDao.findJourneyById(journeyId);
		    System.out.println("ma journey selected " + selectedJourney);
		    return "PaymentForm.xhtml";
		}

}