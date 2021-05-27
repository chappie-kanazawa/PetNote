package com.example.demo.service;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountDao accountDao;

	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	//ユーザー名重複チェック
	@Override
	public boolean isExist(String userName) {
		try {
			Optional<Account> accountOpt = accountDao.findByUserName(userName);
			if(accountOpt.isPresent()) {
				return true;
			} else {
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("指定されたユーザー情報が存在しません");
		}
	}

	//ユーザー新規登録（ユーザー名重複チェックあり）
	@Override
	public void registerAccount(Account account) {
		
		//新規ユーザーのユーザー名が既存ユーザー名と重複していないかチェック
		String registerName = account.getUserName();
		
		//Accountを登録　userNameが重複していれば例外発生
		if(isExist(registerName)) {
			throw new AccountNotFoundException("指定されたユーザー名は使用されています");
		} else {
			accountDao.insert(account);
		}
	}
	
	//ログインしているユーザー情報の取得
	@Override
	public Account findByUserId(int userId){
		//Optional<Account>一件を取得 idが無ければ例外発生　
		try {
			return accountDao.findByUserId(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("指定されたユーザー情報が存在しません");
		}
	}
	
	//ログインしているユーザー情報の取得
	@Override
	public Account findByUserName(String userName){
		try {
			Optional<Account> accountOpt = accountDao.findByUserName(userName);
			if(accountOpt.isPresent()) {
				Account account = accountOpt.orElse(null);
				return account;
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("指定されたユーザー情報が存在しません");
		}
	}
	
	//ログインしているユーザー情報の更新
	@Override
	public void editAccount(Account account) {

		//Accountを更新　idが無ければ例外発生
		if(accountDao.update(account) == 0) {
			throw new AccountNotFoundException("更新するユーザー情報が存在しません");
		}
	}

	//ログインしているユーザー情報の削除
	@Override
	public void deleteAccount(int userId) {

		//Accountを削除 idがなければ例外発生
		if(accountDao.deleteByUserId(userId) == 0) {
			throw new AccountNotFoundException("削除するユーザー情報が存在しません");
		}
	}

}
