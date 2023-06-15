package objects;

import utils.General_Util;

public enum Accounts {
	NEW_ACCOUNT("Automation", "FC", generateRandomEmail(), "123456", "123456"),
	USER_ACCOUNT("anvoqa@gmail.com", "123456"),
	ADMIN("admin@yourstore.com", "admin"),
	FULL_INFO_ACCOUNT("Male", "Automation", "FC", "1999-01-01", generateRandomEmail(), "123456", "123456", "Automation FC");
	
	private String gender;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String password;
	private String confirmPassword;
	private String companyName;
	
	Accounts(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	Accounts(String firstName, String lastName, String email, String password, String confirmPassword){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	Accounts(String gender, String firstName, String lastName, String dob, String email, String password, String confirmPassword, String companyName){
		this.setGender(gender);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setDob(dob);
		this.email = email;
		this.password = password;
		this.setCompanyName(companyName);
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public static String generateRandomEmail() {
		return "afc" + General_Util.generateRadomNumber() + "@yopmail.com";
	}
}
