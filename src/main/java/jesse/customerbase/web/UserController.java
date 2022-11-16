package jesse.customerbase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jesse.customerbase.domain.SignupForm;
import jesse.customerbase.domain.User;
import jesse.customerbase.domain.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {
	
	//Controlleriin otettu mallia Hinkulan serverprogramming materiaaleista
	//Yhdistetty käyttäjä repo toimintoja varten
	@Autowired
    private UserRepository repository; 
	
	//rekisteröinti sivuston /endpoint ohjaa käyttäjän oikealle formille
    @RequestMapping(value = "/signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
    //vastaa uudenkäyttäjän tallentamisesta, validointi pitää huolen, että tiedot ovat oikein
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	
    	if (!bindingResult.hasErrors()) { // validation errors
    		
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
    			//hakee formilta salasanan
	    		String password = signupForm.getPassword();
	    		//Salasanan muuttaja, voidaan muuttaa raakateksti hash muotoon
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	//Hashataan käyttäjän salasana
		    	String hashPassword = bc.encode(password);
	
		    	//luodaan uusi käyttäjä
		    	User newUser = new User();
		    	//asetetaa syötetyt attribuutit käyttäjän tietoihin
		    	newUser.setPasswordHash(hashPassword);
		    	newUser.setUsername(signupForm.getUsername());
		    	//asettaa käyttäjänroolin (käyttäjä ei voi itse valita)
		    	newUser.setRole("ADMIN");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { //tarkistaa onko saman nimisiä käyttäjiä
		    		//tallennetaan käyttäjä jos nimi on uniikki
		    		repository.save(newUser);
		    	}
		    	else {
		    		//annetaan virhe ilmoitus, jos käyttäjä nimi ei ole uniikki
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			//annetaan virhe ilmoitus, jos salasanat eivät vastaa
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		//palautetaan takaisin rekisteröintiin mikäli virheitä
    		return "signup";
    	}
    	//onnistuessa ohjataan käyttäjä takaisin kirjautumis sivulle
    	return "redirect:/login";    	
    }    
    
}
