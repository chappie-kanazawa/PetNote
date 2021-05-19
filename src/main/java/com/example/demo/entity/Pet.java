package com.example.demo.entity;

import java.util.List;

public class Pet {
	private int petId;
	private String petName;
	private String kind;
	private int gender;
	private String petIcon;
	private int userId;
	private List<Record> recList;
	

	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPetIcon() {
		return petIcon;
	}
	public void setPetIcon(String petIcon) {
		this.petIcon = petIcon;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Record> getRecList() {
		return recList;
	}
	public void setRecList(List<Record> recList) {
		this.recList = recList;
	}

	
	
}