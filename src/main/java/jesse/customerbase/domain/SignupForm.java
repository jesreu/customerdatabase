package jesse.customerbase.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//uuden käyttäjän luomista varten oleva rekisteröitymisluokka
public class SignupForm {
	
	//määritellään halutut rajoitukset käyttäjätiedoille
    @NotEmpty
    @Size(min=5, max=30)
    private String username = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String password = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String passwordCheck = "";
    

    //pitää huolen että luotu käyttäjä on user roolissa
    @NotEmpty
    private String role = "ADMIN";

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
