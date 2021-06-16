package com.example.demo.app.login;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Pet;
import com.example.demo.entity.Record;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private int userId;

    @NotNull (message = "ユーザー名を入力してください。")
    private String userName;

    @NotNull (message = "パスワードを入力してください。")
    @Size(min = 4, max = 20, message = "8文字以上20文字以内で入力してください。")
    private String pass;
    
    private String icon;
    
    private String intro;
    
    private List<Pet> petList;
    
    private List<Record> recList;

    private Page<Record> recListPage;
    
    public LoginForm() {}

	public LoginForm(
			int userId,
			String userName,
			String pass,
			String icon,
			String intro,
			List<Pet> petList,
			List<Record> recList,
			Page<Record> recListPage
			) {
		this.userId = userId;
		this.userName = userName;
		this.pass = pass;
		this.icon = icon;
		this.intro = intro;
		this.petList = petList;
		this.recList = recList;
		this.recListPage = recListPage;
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

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}