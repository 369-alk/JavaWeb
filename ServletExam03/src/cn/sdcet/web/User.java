package cn.sdcet.web;

import java.util.Arrays;

public class User {

	private String name;
	private String gender;
	private String education;
	private String[] hobby;
	private String hobbyInput;

	public User() {

	}

	public User(String name, String gender, String education, String[] hobby) {
		this(name, gender, education, hobby, "");
	}

	public User(String name, String gender, String education, String[] hobby, String hobbyInput) {
		this.name = name;
		this.gender = gender;
		this.education = education;
		this.hobby = hobby;
		this.hobbyInput = hobbyInput;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public String getHobbyInput() {
		return hobbyToString();
	}

	public void setHobbyInput(String hobbyInput) {
		this.hobbyInput = hobbyInput;
	}

	public String hobbyToString() {
		return Arrays.toString(hobby);
	}

}
