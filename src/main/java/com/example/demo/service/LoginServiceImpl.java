package com.example.demo.service;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Login;
import com.example.demo.repository.AccountDao;

@Service
public class LoginServiceImpl implements LoginService {

	private final AccountDao accountDao;

	public LoginServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	// ログイン認証
	@Override
	public boolean execute(Login login) {

		try {
			Optional<Account> accountOpt = accountDao.findByUserName(login.getUserName());

			if (accountOpt.isPresent()) {
				// OptionalでラップされたAccount情報を取り出す。
				Account account = accountOpt.orElse(null);

				// 入力されたパスワード
				String enteredPass = login.getPass();
				// 登録されているパスワード
				String registeredPass = account.getPass();

				// パスワードの合致チェック
				if (enteredPass.equals(registeredPass)) {
					return true;
				} 
			}
			return false;
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("指定されたユーザー情報が存在しません");
		}
	}

	// ログインしたユーザー情報の取得
	@Override
	public Account findByUserName(String userName) {

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
}
