package com.example.demo.app.pet;


import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Record;

@Component
public class PetForm {
	
	@NotNull
	@Digits(integer = 5, fraction = 0)
	private int petId;
	
	@NotBlank (message = "ペットのお名前を入力してください。")
    @Size(min = 1, max = 20, message = "20文字以内で入力してください。")
    private String petName;

    @Size(min = 1, max = 20, message = "20文字以内で入力してください。")
    private String kind;
    
    @Digits(integer = 1, fraction = 0)
    private int gender;
    
    private MultipartFile petIconFile;
    
    private String petIcon;
    
    @NotNull
    private int userId;
    
    private List<Record> recList;

	public PetForm() {}

	public PetForm(
			int petId,
			String petName,
			String kind, 
			int gender,
			MultipartFile petIconFile,
			String petIcon,
			int userId,
			List<Record> recList
			) {
		this.petId = petId;
		this.petName = petName;
		this.kind = kind;
		this.gender = gender;
		this.petIconFile = petIconFile;
		this.petIcon = petIcon;
		this.userId = userId;
		this.recList = recList;
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

	public MultipartFile getPetIconFile() {
		return petIconFile;
	}

	public void setPetIconFile(MultipartFile petIconFile) {
		this.petIconFile = petIconFile;
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