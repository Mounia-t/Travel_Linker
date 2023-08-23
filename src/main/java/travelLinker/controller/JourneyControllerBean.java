package travelLinker.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import travelLinker.dao.JourneyDao;
import travelLinker.entity.Journey;
import travelLinker.viewModel.JourneyViewModel;

	
	
@ManagedBean
@RequestScoped
	public class JourneyControllerBean  implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JourneyViewModel journeyVM = new JourneyViewModel();
		
		@Inject
		private JourneyDao journeyDao;
		
		public List<Journey> journeys;
		
		
		
		public void addJourney() {
		    
		    Long id = journeyDao.insert(journeyVM);
		    System.out.println("Journey created with id : " + id);
		    

		}
		
		public List<Journey> getjourneys (){
			return journeyDao.getAllJourneys();
			
		}

		  public JourneyControllerBean() {
		        journeys = new ArrayList<>();
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

		
		public List<Journey> getJourneys() {
			return journeys;
		}

		public void setJourneys(List<Journey> journeys) {
			this.journeys = journeys;
		}

	}


