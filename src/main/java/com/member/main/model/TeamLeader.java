package com.member.main.model;

import java.util.Objects;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TeamLeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
private int lId;

	public TeamLeader() {
		super();
	}
	public TeamLeader(int tId, String firstName, String lastName, String email, String password, int lId) {
		super();
		this.tId = tId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.lId = lId;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
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
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	@Override
	public String toString() {
		return "TeamLeader [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", lId=" + lId + "]";
	}
	
}
