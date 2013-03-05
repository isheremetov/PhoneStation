package by.gsu.isheremetov.phonestation.models;

public class User {
	public final static String USERROLE_ADMIN = "Admin";
	public final static String USERROLE_USER = "User";

	private int id;
	private String phone;
	private String password;
	private String username;
	private String email;
	private float purse;
	private String role;
	private Boolean active;

	public User(int id, String phone, String password, String username,
			String email, float purse, String role, Boolean active) {
		setId(id);
		setPhone(phone);
		setPassword(password);
		setUsername(username);
		setEmail(email);
		setPurse(purse);
		setRole(role);
		setActive(active);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public float getPurse() {
	    return purse;
	}

	public void setPurse(float purse) {
	    this.purse = purse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
