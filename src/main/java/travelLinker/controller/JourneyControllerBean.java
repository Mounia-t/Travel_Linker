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
		
		public List<Journey> journeys;
		public void addJourney() {
		    try {
		        if (imageFile != null) {
		            // Traitement de l'upload de l'image
		            String uploadsDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("C:\\Users\\33665\\P3Repo\\Travel_Linker\\src\\main\\webapp\\images");
		            String fileName = "journey_" + System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(imageFile.getSubmittedFileName()) + "."
		                    + FilenameUtils.getExtension(imageFile.getSubmittedFileName());
		            String filePath = Paths.get(uploadsDir, fileName).toString();

		            try (InputStream input = imageFile.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
		                IOUtils.copy(input, output);
		            }

		            journeyVM.setImagePath("C:\\Users\\33665\\P3Repo\\Travel_Linker\\src\\main\\webapp\\images" + fileName); // Utilisez le chemin relatif de l'image
		        }

		        // Appel à la méthode de la couche de persistance pour ajouter le voyage
		        Long id = journeyDao.insert(journeyVM);

		        System.out.println("Journey created with id : " + id);
		        clear();
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
		
		


	}


