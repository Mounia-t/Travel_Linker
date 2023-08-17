package travelLinker.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.servlet.http.Part;

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

			if (imageFile != null) {
	            try {
	                InputStream imageInputStream = imageFile.getInputStream();
	                byte[] imageFile = IOUtils.toByteArray(imageInputStream);

	                // Utilisez l'objet journeyVM existant pour stocker les données de l'image
	                journeyVM.setImage(imageFile);
	            } catch (IOException e) {
	                e.printStackTrace();
	                // Gérez l'exception selon vos besoins
	            }
	            System.out.println("Image created" + imageFile);
	        }
			Long id= journeyDao.insert(journeyVM);
			System.out.println("Journey created with id : " + id);
			clear();
		}
			                
		public void clear() {
			journeyVM = new JourneyViewModel();
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
		
		
		


	}


