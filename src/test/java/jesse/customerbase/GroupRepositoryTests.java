package jesse.customerbase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jesse.customerbase.domain.Group;
import jesse.customerbase.domain.GroupRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GroupRepositoryTests {
	
	@Autowired
    private GroupRepository repository;

	//tarkistetaan löytääkö findbyName ryhmän
    @Test
    public void findByNameShouldReturnGroup() {
        List<Group> groups = repository.findByName("Kanta-asiakas");
        
        assertThat(groups).hasSize(1);
        assertThat(groups.get(0).getGroupid()).isEqualTo(1);
    }
    //tarkistetaan voidaanko luoda uusi ryhmä
    @Test
    public void createNewGroup() {
    	Group Group = new Group("Henkilökunta");
    	repository.save(Group);
    	assertThat(Group.getGroupid()).isNotNull();
    }
    //tarkistetaan voidaanko poistaa ryhmä
    @Test
    public void deleteGroup() {
    	List<Group> groups = repository.findByName("Kanta-asiakas");
    	Group Group = groups.get(0);
		repository.delete(Group);
		List<Group> updatedgroups = repository.findByName("Kanta-asiakas");
		assertThat(updatedgroups).hasSize(0);
     }
}