package com.example.demo.app.record;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RecForm {
	
	@NotNull
	@Digits(integer = 5, fraction = 0)
	private int recId;
	
	@NotBlank (message = "成長記録を入力してください。")
    @Size(min = 1, max = 140, message = "140文字以内で入力してください。")
    private String comment;

    private MultipartFile recPicFile;
    
    private String recPic;
    
    @NotNull (message = "記録日を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String recDate;

    @NotNull (message = "ペットを選択してください。")
	//@Pattern(regexp ="^([0-9])+$", message= "ペットを選択してください。")
    @Digits(integer = 5, fraction = 0)
    private int petId;
    
    private String petName;
    
    public RecForm() {}

	public RecForm(
			int recId,
			String comment,
			MultipartFile recPicFile,
			String recPic,
			String recDate,
		    int petId,
		    String petName
			) {
		this.recId = recId;
		this.comment = comment;
		this.recPicFile = recPicFile;
		this.recPic = recPic;
		this.recDate = recDate;
		this.petId = petId;
		this.petName = petName;
	}

    
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public MultipartFile getRecPicFile() {
		return recPicFile;
	}
	public void setRecPicFile(MultipartFile recPicFile) {
		this.recPicFile = recPicFile;
	}
	
	public String getRecPic() {
		return recPic;
	}

	public void setRecPic(String recPic) {
		this.recPic = recPic;
	}

	public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = recDate;
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
	

}