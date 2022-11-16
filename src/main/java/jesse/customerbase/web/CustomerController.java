package jesse.customerbase.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jesse.customerbase.domain.Customer;
import jesse.customerbase.domain.CustomerRepository;
import jesse.customerbase.domain.Group;
import jesse.customerbase.domain.GroupRepository;

@Controller
public class CustomerController {
	
	//Lainattu toimiva ratkaisu edelliselle sivulle palauttamista varten stackoverflowsta
	//https://stackoverflow.com/questions/804581/spring-mvc-controller-redirect-to-previous-page
	//Mahdollistaa /save endpointin käytön molempien add ja edit toimintojen kanssa 
	//koodissa olessa validointi toteuttettuna
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
	{
	   return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
	}
	
	//yhdistetään tarvittavat repositoryt autowiredilla controlleriin, niin metodit pääsevät käyttämään
	//repositorioiden toimintoja
	@Autowired
	private CustomerRepository crepository;

	@Autowired
	private GroupRepository grepository;
	
	//määrittelee kirjautumissivun
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

	// Listasivun controlleri endpointteille / ja customerlist
	@RequestMapping(value = { "/", "/customerlist" })
	public String customerList(Model model) {
		model.addAttribute("customers", crepository.findAll());
		return "customerlist";
	}
	
	// Ryhmien listaussivu
	@RequestMapping(value = "/grouplist", method = RequestMethod.GET)
	public String groupList(Model model) {
		model.addAttribute("groups", grepository.findAll());
		return "grouplist";
	}

	// Asiakkaan lisäämisen controlleri
	@RequestMapping(value = "/addcustomer", method = RequestMethod.GET)
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("groups", grepository.findAll());
		return "addcustomer";
	}

	// Asiakkaan tallentamisen controlleri, validoinnilla. Ei anna tallentaa, mikäli data on väärin
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Customer customer, BindingResult bindingResult, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("groups", grepository.findAll());
			return getPreviousPageByRequest(request).orElse("/");
		}
		
		crepository.save(customer);
		return "redirect:customerlist";
	}
	//Asiakkaan poistaminen, vaatiin poistettavan asiakkaan id:n ja Admin tason auktoriteetin
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
    public String deleteCustomer(@PathVariable("id") Long Id, Model model) {
    	crepository.deleteById(Id);
        return "redirect:/customerlist";
    }
    //Asiakkaan tietojen muokkaaminen
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable("id") Long Id, Model model) {
    	model.addAttribute("customer", crepository.findById(Id));
    	model.addAttribute("groups", grepository.findAll());
        return "editcustomer";
    }

	
	// Ryhmän lisäämisen controlleri
	@RequestMapping(value = "/addgroup", method = RequestMethod.GET)
	public String addGroup(Model model) {
		model.addAttribute("group", new Group());
		return "addgroup";
	}
	
	// Ryhmän tallentamisen controlleri validoinilla
	@RequestMapping(value = "/savegroup", method = RequestMethod.POST)
	public String saveGroup(@Valid Group group, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addgroup";
		}
		grepository.save(group);
		return "redirect:grouplist";
	}
	
	//tietyn asiakasryhmän asiakkaiden listaus
	@RequestMapping(value = "/grouplist/{id}", method = RequestMethod.GET)
	public String customerByGroupList(@PathVariable("id") Long Id, Model model) {
		Group group = grepository.findByGroupid(Id);
		model.addAttribute("customers", crepository.findByGroup(group));
		return "customerlist";
	}

}
