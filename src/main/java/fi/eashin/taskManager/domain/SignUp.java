package fi.eashin.taskManager.domain;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

public class SignUp{
	
	@NotEmpty
    @Size(min=4, max=10)
    private String username = "";

    @NotEmpty
    @Size(min=4, max=10)
    private String password = "";

    @NotEmpty
    @Size(min=4, max=10)
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
