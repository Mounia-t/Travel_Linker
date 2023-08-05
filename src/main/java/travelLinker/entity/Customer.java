package travelLinker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
	public class Customer {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id; 
		private String firstName; 
		private String lastName; 
		private String address; 
		private String email;

		private Long accountId;
		@OneToOne
		private Account account;
		
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


		
	}

