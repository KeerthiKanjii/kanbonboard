package com.member.main.entity;
 
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeamMember {

	@Id
	@GeneratedValue
	private int lId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	private int tId;
	public TeamMember() {
		super();

	}
	public TeamMember(int lId, String firstName, String lastName, String email, String password, int tId) {
		super();
		this.lId = lId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.tId = tId;
	}
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	@Override
	public String toString() {
		return "TeamMember [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", tId=" + tId + "]";
	}
	
	
}