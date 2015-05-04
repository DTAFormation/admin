package com.dta.addClient;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="addClient")
public class ManagedClient {
	String name;
	String firstName;
	String passWord;
	int age;
	String address;
	
	@Override
	public String toString() {
		return "ManagedClient name=" + name + ", firstName=" + firstName
				+ ", passWord=" + passWord + ", age=" + age + ", address="
				+ address ;
	}


	public void save(){
		System.out.println(toString());
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
