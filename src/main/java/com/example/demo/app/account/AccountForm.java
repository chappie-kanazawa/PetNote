package com.example.demo.app.account;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class AccountForm {

    @Digits(integer = 5, fraction = 0)
    private int userId;

	@Pattern(regexp ="^([a-zA-Z0-9]{4,20})$", message= "4文字以上20文字以内の半角英数字をご使用下さい。")
    @NotBlank (message = "ユーザー名を入力してください。")
    //@Size(min = 4, max = 20, message = "4文字以上20文字以内で入力してください。")
    private String userName;

	@Pattern(regexp ="^([a-zA-Z0-9]{4,20})$", message= "4文字以上20文字以内の半角英数字をご使用下さい。")
    @NotBlank (message = "パスワードを入力してください。")
    //@Size(min = 4, max = 20, message = "4文字以上20文字以内で入力してください。")
    private String pass;

    @NotNull (message = "アイコン画像を設定してください。")
    private MultipartFile iconFile;
    
    private String icon;

    //@NotNull (message = "自己紹介文を入力してください。")
    @Size(min = 1, max = 20, message = "255文字以内で入力してください。")
    private String intro;

    public AccountForm() {}

	public AccountForm(
			int userId,
			String userName,
			String pass, 
			MultipartFile iconFile,
			String icon,
			String intro
			) {
		this.userId = userId;
		this.userName = userName;
		this.pass = pass;
		this.iconFile = iconFile;
		this.icon = icon;
		this.intro = intro;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public MultipartFile getIconFile() {
		return iconFile;
	}

	public void setIconFile(MultipartFile iconFile) {
		this.iconFile = iconFile;
	}
	

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	
}