package jesse.customerbase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//Repository luokka customer olioille spring boottia varten
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByGroup(Group group);
	List<Customer> findByFname(@Param("fname")String fname);

}
