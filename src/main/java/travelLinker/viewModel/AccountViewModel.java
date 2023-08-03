package travelLinker.viewModel;



import travelLinker.entity.AccountBean;
import travelLinker.entity.RoleUser;

public class AccountViewModel {

	private String email;
	private String password;
	private RoleUser role; 
	
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
}
