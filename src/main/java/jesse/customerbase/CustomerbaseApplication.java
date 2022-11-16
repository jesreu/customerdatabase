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

	@Bean
	public CommandLineRunner CmdRunner(CustomerRepository crepository,GroupRepository grepository, UserRepository urepository) {
		return (args) -> {
			//tallennetaan hieman testidataa
			//lisää ryhmän kanta-asiakas
			Group group1 = new Group("Kanta-asiakas");
			grepository.save(group1);
			//lisää yhden asiakkaan
			crepository.save(new Customer("Matti", "Meikäläinen", 22, "matti.meikalainen@gmail.com", "+358020202", group1));
			
			// Lisätään pari käyttäjää
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@kayttaja.test", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@kayttaja.test", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};

	}
}
