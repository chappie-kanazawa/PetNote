package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountDao;

import lombok.var;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.StrictStubs.class)
@DisplayName("AccountServiceImplの単体テスト")
class AccountServiceUnitTest {

    @Mock // モック(stub)クラス ダミーオブジェクト
    private AccountDao dao;

    @InjectMocks // テスト対象クラス　モックを探す newする
    private AccountServiceImpl accountServiceImpl; 

    @Test // テストケース
    @DisplayName("ユーザー名重複チェック(正常系)")
    void testIsExist1() {
    	boolean result1 = accountServiceImpl.isExist("taro");
    	assertNotNull(result1);
    	assertTrue(result1);
    }
    
    @Test // テストケース
    @DisplayName("ユーザー名重複チェック(異常系)")
    void testIsExist2() {
    	boolean result2 = accountServiceImpl.isExist("xxxx");
    	assertFalse(result2);
    }
    
    @Test // テストケース
    @DisplayName("新規登録したいユーザー名が重複している場合のテスト")
        // テスト名
    void testRegisterAccountThrowException() {
    	
        var account1 = new Account();
        account1.setUserId(3);
        account1.setUserName("taro");
        account1.setPass("1111test3");
        account1.setIcon("xxxxtest3");
        account1.setIntro("よろしくtest3");

        // モッククラスのI/Oをセット
        //when(dao.insert()).thenReturn();

      //ユーザー名が重複しているいとAccountNotFoundExceptionが発生することを検査
        try {
        	accountServiceImpl.registerAccount(account1);
        } catch (AccountNotFoundException e) {
        	assertEquals(e.getMessage(), "指定されたユーザー名は使用されています");
        }
    }

    @Test // テストケース
    @DisplayName("ログインしているユーザー情報の取得ができない場合のテスト")
        // テスト名
    void testGetAccountThrowException() {

        // モッククラスのI/Oをセット
        //when(dao.findByUserId(0)).thenThrow(new EmptyResultDataAccessException(1));

      //ユーザー情報が取得できないとAccountNotFoundExceptionが発生することを検査
        try {
        	accountServiceImpl.findByUserId(3);
        } catch (AccountNotFoundException e) {
        	assertEquals(e.getMessage(), "指定されたユーザー情報が存在しません");
        }
    }
    
    @Test // テストケース
    @DisplayName("更新するユーザー情報が取得できない場合のテスト")
        // テスト名
    void testGetUpdateAccountThrowException() {

        var account2 = new Account();
        account2.setUserId(3);
        account2.setUserName("taro");
        account2.setPass("1111test3");
        account2.setIcon("xxxxtest3");
        account2.setIntro("よろしくtest3");  	
    	
        // モッククラスのI/Oをセット
        //when(dao.findByUserId(0)).thenThrow(new EmptyResultDataAccessException(1));

      //ユーザー情報が取得できないとAccountNotFoundExceptionが発生することを検査
        try {
        	accountServiceImpl.editAccount(account2);
        } catch (AccountNotFoundException e) {
        	assertEquals(e.getMessage(), "更新するユーザー情報が存在しません");
        }
    }

    @Test // テストケース
    @DisplayName("削除するユーザー情報が取得できない場合のテスト")
        // テスト名
    void testGetDeleteAccountThrowException() {	
    	
        // モッククラスのI/Oをセット
        //when(dao.findByUserId(0)).thenThrow(new EmptyResultDataAccessException(1));

      //ユーザー情報が取得できないとAccountNotFoundExceptionが発生することを検査
        try {
        	accountServiceImpl.deleteAccount(3);
        } catch (AccountNotFoundException e) {
        	assertEquals(e.getMessage(), "削除するユーザー情報が存在しません");
        }
    }
    
}
