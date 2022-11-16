package jesse.customerbase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//Repository luokka group olioille spring boottia varten
public interface GroupRepository extends CrudRepository<Group, Long> {
	//etsii ryhmän id:n perusteella
	Group findByGroupid(Long groupid);
	//etsii ryhmän nimen perusteella
	List<Group> findByName(String name);
	
}
