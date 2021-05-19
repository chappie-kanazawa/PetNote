package com.example.demo.service;


import com.example.demo.entity.Account;

public interface AccountService {
	
	boolean isExist(String userName);
	
	void registerAccount(Account account);
	
	Account findByUserId(int userId);
	
	Account findByUserName(String userName);

	void editAccount(Account account);

	void deleteAccount(int userId);

}
