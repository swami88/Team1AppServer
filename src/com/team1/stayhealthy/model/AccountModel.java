package com.team1.stayhealthy.model;

import java.io.Serializable;
import java.util.Date;


public class AccountModel implements Serializable{
	private static final long serialVersionUID = -1430163807755003319L;
	private String username;
	private String password;
	private String email;
	private String name;
	private Date birthDate = new Date();
	private String height = "180 cm";
	private String weight = "60 kg";
	private boolean Hypertension= false;
	private boolean Diabetes= false;
	private boolean insomnia= false;
	private boolean cardio= false;
	private boolean gender = false;
	private boolean remenber = false;
	private boolean newUser = true;
	
	
	
	public AccountModel() {
		
	}
	
	public AccountModel(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.remenber = false;
	}
	
	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public boolean isHypertension() {
		return Hypertension;
	}

	public void setHypertension(boolean hypertension) {
		Hypertension = hypertension;
	}

	public boolean isDiabetes() {
		return Diabetes;
	}

	public void setDiabetes(boolean diabetes) {
		Diabetes = diabetes;
	}

	public boolean isInsomnia() {
		return insomnia;
	}

	public void setInsomnia(boolean insomnia) {
		this.insomnia = insomnia;
	}

	public boolean isCardio() {
		return cardio;
	}

	public void setCardio(boolean cardio) {
		this.cardio = cardio;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}



	public void setRemenber(boolean remenber){
		this.remenber = remenber;
	}
	
	public boolean isRemenbered(){
		return this.remenber;
	}
	
	public AccountModel(String username){
		this.username=username;
	}
	
	public boolean checkLogin(String user, String pass){
		return (username.equals(user) && password.equals(pass) );
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
