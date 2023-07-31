package travelLinker.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
	public class CustomerBean {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id; 
		private String firstName; 
		private String lastName; 
		private String address; 
//		private AccountBean accountBean;
		
		public Long getId() {
			return id;
		}	
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

		
	}

