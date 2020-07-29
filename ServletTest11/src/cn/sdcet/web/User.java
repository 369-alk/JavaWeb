package cn.sdcet.web;

public class User {

	private String mail;
	private String blog;
	private String name;
	private String password1;
	private String password2;
	private String gender;
	private String address;

	public User() {

	}

	public User(String mail, String blog, String name, String password1, String password2, String gender,
			String adress) {
		super();
		this.mail = mail;
		this.blog = blog;
		this.name = name;
		this.password1 = password1;
		this.password2 = password2;
		this.gender = gender;
		this.address = adress;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
