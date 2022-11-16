package jesse.customerbase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jesse.customerbase.domain.Customer;
import jesse.customerbase.domain.CustomerRepository;
import jesse.customerbase.domain.Group;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTests {
	
	@Autowired
    private CustomerRepository repository;

	//tarkistetaan löytääkö repon findByGroup asiakkaan tietoja
    @Test
    public void findByGroupShouldReturnCustomer() {
        List<Customer> customers = repository.findByFname("Matti");   
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getLname()).isEqualTo("Meikäläinen");
    }
    //tarkistetaan voidaan luoda uusi asiakas
    @Test
    public void createNewCustomer() {
    	Customer customer = new Customer("Esa", "Esimerkki", 11, "esa.esimerkki@hotmail.com", "+358213456781", new Group("Henkilökunta"));
    	repository.save(customer);
    	assertThat(customer.getId()).isNotNull();
    }
    //tarkistetaan voidaanko poistaa asiakas
    @Test
    public void deleteNewCustomer() {
    	List<Customer> customers = repository.findByFname("Matti");
    	Customer customer = customers.get(0);
		repository.delete(customer);
		List<Customer> updatedCustomers = repository.findByFname("Matti");
		assertThat(updatedCustomers).hasSize(0);
     }
}

