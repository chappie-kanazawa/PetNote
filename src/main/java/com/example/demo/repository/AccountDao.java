package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Login;

public interface AccountDao {
	
	List<Account> findAll();

	Account findByUserId(int userId);
	
	Optional<Account> findByUserName(String userName);
	
	Optional<Account> findByLogin(Login login);

	void insert(Account account);

	int update(Account account);

	int deleteByUserId(int userId);	
	
}
