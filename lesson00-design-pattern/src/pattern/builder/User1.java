package pattern.builder;

// OWNER CLASS
public class User1 {

		private Integer id;
		private String firstName;
		private String lastName;
		private Integer age;
		private String email;
		private String address;

	public User1() {
	}

	// Required: id, firstName, lastName
	// Required: id, email

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
	
	// NESTED CLASS --> class có nhiệm vụ khởi tạo đối tượng chứa các thông tin của User1
	//              --> truyền các giá trị này qua cho user1
	public static class Builder {
		// B1: copy tất cả các thuộc tính từ OWNER CLASS <User1>
		private Integer id;
		private String firstName;
		private String lastName;
		private Integer age;
		private String email;
		private String address;
		
		// B2: cho class này có private constructor, để dùng cho chính nó hoặc User1
		private Builder() {
		}
		
		// B3: tạo các hàm setter(return Builder) cho class Builder
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
