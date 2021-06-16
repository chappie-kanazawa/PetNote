package com.example.demo.entity;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;

public class Pet implements Comparable<Pet> {
	private int petId;
	private String petName;
	private String kind;
	private int gender;
	private String petIcon;
	private int userId;
	private List<Record> recList;
	private Page<Record> recListPage;
	
	//Listにする際の自然順序付けを日本語昇順に設定
    public int compareTo(Pet pet) {
        Collator japanCollation = Collator.getInstance( Locale.JAPANESE );
        int temp = 0;
        temp = japanCollation.compare(this.getPetName(), pet.getPetName());
        return temp;
    }

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

	public Page<Record> getRecListPage() {
		return recListPage;
	}

	public void setRecListPage(Page<Record> recListPage) {
		this.recListPage = recListPage;
	}

	
	
}