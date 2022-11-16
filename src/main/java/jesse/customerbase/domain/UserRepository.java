package jesse.customerbase.domain;

import org.springframework.data.repository.CrudRepository;

//Repository sivuston käyttäjä-olioille
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}

