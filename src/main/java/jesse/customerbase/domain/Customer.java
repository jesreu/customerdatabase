package jesse.customerbase.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer {
	
	
	//Luokan attribuutit sisältäen asiakasryhmän, joka liitetty group.javasta
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Size(min=2, max=30)
	private String fname;
	@NotNull
	@Size(min=2, max=30)
	private String lname;
	@NotNull
	@Min(0)
	private int age;
	private String email;
	private String phone;
	
	@ManyToOne
    @JoinColumn(name = "groupid")
	private Group group;
	
	
	//Tyhjä konstruktori
	public Customer(){}
	
	//Konstruktori attribuuteilla (ei id:tä)
	public Customer(String fname, String lname, int age, String email, String phone, Group group) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.group = group;
	}
	
	//Get ja Set toiminnallisuus ID attribuutille
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//Get ja Set toiminnallisuus etunimi attribuutille
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	//Get ja Set toiminnallisuus sukunimi attribuutille
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	//Get ja Set toiminnallisuus ikä attribuutille
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//Get ja Set toiminnallisuus sposti attribuutille
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Get ja Set toiminnallisuus puhelin attribuutille
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//Get ja Set toiminnallisuus (asiakas)ryhmä attribuutille
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
