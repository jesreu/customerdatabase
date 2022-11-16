package jesse.customerbase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jesse.customerbase.domain.Customer;
import jesse.customerbase.domain.CustomerRepository;
import jesse.customerbase.domain.Group;
import jesse.customerbase.domain.GroupRepository;
import jesse.customerbase.domain.User;
import jesse.customerbase.domain.UserRepository;

@SpringBootApplication
public class CustomerbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerbaseApplication.class, args);
	}
}
