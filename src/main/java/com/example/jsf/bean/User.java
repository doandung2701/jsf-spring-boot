package com.example.jsf.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class User {
	@NotNull(message = "{email.notempty}")
	private String name;
	@Min(18)
	private int age;
	@NotNull(message = "{email.notempty}")
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public User(String name, int age, String mobile) {
		super();
		this.name = name;
		this.age = age;
		this.mobile = mobile;
	}

	public User() {
		super();
	}

}