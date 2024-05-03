package pattern.builder;

public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String address;

	public User() {
	}

	// Required: id, firstName, lastName
	public User(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Required: id, email
	public User(Integer id, String email) {
		this.id = id;
		this.email = email;
	}
	
	// Required: id, email, address --> chịu <duplicate method User> --> BUILDER PATTERN !
	/* public User(Integer id, String email, String address) {
		 this.id = id;
		 this.email = email;
		 this.address = address;
	} */

	public User(Integer id, String firstName, String lastName, Integer age, String email, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email="
				+ email + ", address=" + address + "]";
	}

}
