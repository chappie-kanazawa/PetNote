package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Login;


@Repository
public class AccountDaoImpl implements AccountDao {

	private final JdbcTemplate jdbcTemplate;

	public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Account> findAll() {

		String sql = "SELECT a.user_id, a.user_name, a.pass, a.icon, a.intro "
				+ "FROM account a ";

		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		//return用の空のListを用意
		List<Account> list = new ArrayList<>();

		//二つのテーブルのデータをTaskにまとめる
		for(Map<String, Object> result : resultList) {

			Account account = new Account();
			//account.setUserId((int)result.get("user_id"));
			account.setUserName((String)result.get("user_name"));
			account.setPass((String)result.get("pass"));
			account.setIcon((String)result.get("icon"));
			account.setIntro((String)result.get("intro"));
			
			list.add(account);
		}
		
		return list;
	}
	
	//ログインしているアカウント情報を見つけてくる
	@Override
	public Account findByUserId(int userId) {
		String sql = "SELECT account.user_id, account.user_name, account.pass, account.icon, account.intro "
				+ "FROM account　"
				+ "WHERE account.user_id = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId);
		
		Account account = new Account();
		//account.setUserId((int)result.get("user_id"));
		account.setUserName((String)result.get("user_name"));
		account.setPass((String)result.get("pass"));
		account.setIcon(((String)result.get("icon")));
		account.setIntro((String)result.get("intro"));
		
		return account;
	}

	//新規登録しているユーザーが重複していないか調べる時に使う
	@Override
	public Optional<Account> findByUserName(String userName) {
		String sql = "SELECT user_id, user_name, pass, icon, intro "
				+ "FROM account　"
				+ "WHERE user_name = ?";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, userName);
		
		// アカウントが取得できなかった、または複数取得できたらNG
		if(resultList.size() != 1) {
			return Optional.empty();
			
		} else {
		
			// 1個だけ存在することがわかっているので安心して0個めを取得できる
			Account account = new Account();
			account.setUserId((int)resultList.get(0).get("user_id"));
			account.setUserName((String)resultList.get(0).get("user_name"));
			account.setPass((String)resultList.get(0).get("pass"));
			account.setIcon((String)resultList.get(0).get("icon"));
			account.setIntro((String)resultList.get(0).get("intro"));
					
			//accountをOptionalでラップする
			Optional<Account> accountOpt = Optional.ofNullable(account);
			
			return accountOpt;
		}
	}
	
	//ログイン認証をする
	@Override
	public Optional<Account> findByLogin(Login login) {
		String sql = "SELECT user_id, user_name, pass, icon, intro "
				+ "FROM account　"
				+ "WHERE user_name = ?";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, login.getUserName());
		
		// アカウントが取得できなかった、または複数取得できたらNG
		if(resultList.size() != 1) {
			return Optional.empty();
			
		} else {
		
			// 1個だけ存在することがわかっているので安心して0個めを取得できる
			Account account = new Account();
			account.setUserId((int)resultList.get(0).get("user_id"));
			account.setUserName((String)resultList.get(0).get("user_name"));
			account.setPass((String)resultList.get(0).get("pass"));
			account.setIcon((String)resultList.get(0).get("icon"));
			account.setIntro((String)resultList.get(0).get("intro"));
					
			//accountをOptionalでラップする
			Optional<Account> accountOpt = Optional.ofNullable(account);
			
			return accountOpt;
		}
	}
	

	//新しいユーザーを1件追加
	@Override
	public void insert(Account account) {
		jdbcTemplate.update("INSERT INTO account(user_name, pass, icon, intro) VALUES(?, ?, ?,?)",
				account.getUserName(), account.getPass(), account.getIcon(), account.getIntro() );
	}

	//ログインしているアカウントの情報を更新
	@Override
	public int update(Account account) {
		return jdbcTemplate.update("UPDATE account SET user_name = ?, pass = ?, icon = ?, intro = ? WHERE user_id = ?",
				account.getUserName(), account.getPass(), account.getIcon(), account.getIntro(), account.getUserId() );
	}

	//ログインしているアカウントの削除
	@Override
	public int deleteByUserId(int userId) {
		return jdbcTemplate.update("DELETE FROM account WHERE user_id = ?", userId);
	}


}
