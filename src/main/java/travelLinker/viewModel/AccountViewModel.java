package travelLinker.viewModel;

import travelLinker.entity.Account;
import travelLinker.entity.RoleUser;

public class AccountViewModel {

	private Long id;
	private String email;
	private String password;
	private RoleUser role;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String siret;
	private String companyName;
	private String address;
	private String recepientEmail;
	private String senderEmail;
	private String messageResume;
	private String taskContent;
	private String imagePath;
	private boolean connected;
	
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getDest() {
		return dest;
	}

	public void setDest(Account dest) {
		this.dest = dest;
	}

	private String content;

	private Account sender;

	private Account dest;

	public Long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleUser getRole() {
		return role;
	}

	public void setRole(RoleUser role) {
		this.role = role;
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

	public void setId(Long accountId) {

		this.id = accountId;

	}

	public String getRecepientEmail() {
		return recepientEmail;
	}

	public void setRecepientEmail(String recepientEmail) {
		this.recepientEmail = recepientEmail;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getMessageResume() {
		return messageResume;
	}

	public void setMessageResume(String messageResume) {
		this.messageResume = messageResume;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
