package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.entity.Account;
import com.example.demo.entity.Login;

import lombok.var;

@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("unit")
@Sql
class AccountDaoImplTest {

    @Autowired
    private AccountDaoImpl accountDao;

    @Test
    @DisplayName("findAllのテスト")
    void findAll() {
        var list = accountDao.findAll();

        // 件数のチェック
        assertEquals(2, list.size());

        // 2件目のレコードの取得(ORDER BYが正しく反映されているか)
        var account2 = list.get(1);
        assertNotNull(account2);

        // 各カラムの値が正しくセットされているか
        assertEquals("hanakotest", account2.getUserName());
        assertEquals("2222test", account2.getPass());
        assertEquals("yyyytest", account2.getIcon());
        assertEquals("はじめましてtest", account2.getIntro());

        var account3 = list.get(1);
        assertNotNull(account3);

        assertEquals("hanakotest", account3.getUserName());

    }
    
    @Test
    @DisplayName("findByUserIdのテスト(正常系)")
    void findByUserId1() {
        var account1 = accountDao.findByUserId(0);

        // レコードの存在チェック
        assertNotNull(account1);

        // 各カラムの値が正しくセットされているか
        assertEquals("tarotest", account1.getUserName());
        assertEquals("1111test", account1.getPass());
        assertEquals("xxxxtest", account1.getIcon());
        assertEquals("よろしくtest", account1.getIntro());
    }

    @Test
    @DisplayName("findByUserIdのテスト(レコードが取得できない場合)")
    void findByUserId2() {
        // レコードが取得できず例外がスローされるか
        assertThrows(EmptyResultDataAccessException.class, () -> accountDao.findByUserId(10));
    }
 
    @Test
    @DisplayName("findByUserNameのテスト(正常系)")
    void findByUserName1() {
        var accountOpt1 = accountDao.findByUserName("tarotest");

        // レコードの存在チェック
        assertNotNull(accountOpt1);
        
        Account account1 = accountOpt1.orElse(null);

        // 各カラムの値が正しくセットされているか
        assertEquals(0, account1.getUserId());        
        assertEquals("tarotest", account1.getUserName());
        assertEquals("1111test", account1.getPass());
        assertEquals("xxxxtest", account1.getIcon());
        assertEquals("よろしくtest", account1.getIntro());
    }
    
    @Test
    @DisplayName("findByUserNameのテスト(レコードが取得できない場合)")
    void findByUserName2() {
    	// レコードが取得できず空のoptionalが返ってくるか
    	assertEquals(Optional.empty(), accountDao.findByUserName("xxxx"));
//        // レコードが取得できず例外がスローされるか
//        assertThrows(EmptyResultDataAccessException.class, () -> accountDao.findByUserName("xxxx"));
    }
    
    @Test
    @DisplayName("findByLoginのテスト（正常系）")
    void findByLogin1() {
    	var login1 = new Login("tarotest", "1111test");
        Optional<Account> accountOpt = accountDao.findByLogin(login1);
        
        Account account1 = accountOpt.get();

        // レコードの存在チェック
        assertNotNull(account1);

        // 各カラムの値が正しくセットされているか
        assertEquals("tarotest", account1.getUserName());
        assertEquals("1111test", account1.getPass());
        assertEquals("xxxxtest", account1.getIcon());
        assertEquals("よろしくtest", account1.getIntro());
        
    }

    @Test
    @DisplayName("findByLoginのテスト(レコードが取得できない場合)")
    void findByLogin2() {
        // レコードが取得でき	ない場合、nullが返ってくるか
       	Login login2 = new Login("tarotestx", "1111test");
       	assertEquals(Optional.empty(), accountDao.findByLogin(login2));
        // assertThrows(EmptyResultDataAccessException.class, () -> accountDao.findByLogin(login2));
    }

    
    
    @Test
    @DisplayName("insertのテスト(正常系)")
    void insert() {
        var account = new Account();
        account.setUserId(3);
        account.setUserName("tarotest3");
        account.setPass("1111test3");
        account.setIcon("xxxxtest3");
        account.setIntro("よろしくtest3");

        accountDao.insert(account);

        //assertEquals(1, insertCount);

        var list = accountDao.findAll();

        // 件数のチェック
        assertEquals(3, list.size());

        // 登録されたレコードの取得
        var accountx = list.get(2);

        // 各カラムの値が正しくセットされているか
        assertEquals(account.getUserName(), accountx.getUserName());
        assertEquals(account.getPass(), accountx.getPass());
        assertEquals(account.getIcon(), accountx.getIcon());
        assertEquals(account.getIntro(), accountx.getIntro());
        
    }

    @Test
    @DisplayName("updateのテスト(正常系)")
    void update1() {
        var account = new Account();
        account.setUserId(1);
        account.setUserName("hanakotestx");
        account.setPass("2222testx");
        account.setIcon("yyyytestx");
        account.setIntro("こんにちはtestx");

        var updateCount = accountDao.update(account);

        assertEquals(1, updateCount);

        var account2 = accountDao.findByUserId(1);

        // レコードの存在チェック
        assertNotNull(account2);

        // 各カラムの値が正しくセットされているか
        assertEquals(account.getUserName(), account2.getUserName());
        assertEquals(account.getPass(), account2.getPass());
        assertEquals(account.getIcon(), account2.getIcon());
        assertEquals(account.getIntro(), account2.getIntro());
    }

    @Test
    @DisplayName("updateのテスト(更新対象がない場合)")
    void update2() {
        var account = new Account();
        account.setUserId(10);
        var updateCount = accountDao.update(account);
        assertEquals(0, updateCount);
    }

    @Test
    @DisplayName("deleteByUserIdのテスト(正常系)")
    void deleteByUserId1() {
        accountDao.deleteByUserId(1);

        var list = accountDao.findAll();

        // 件数のチェック(対象外のレコードまで消えていないかチェック)
        assertEquals(1, list.size());

        // レコードが取得できないことを確認
        assertThrows(EmptyResultDataAccessException.class, () -> accountDao.findByUserId(1));
    }

    @Test
    @DisplayName("deleteByUserIdのテスト(更新対象がない場合)")
    void deleteByUserId2() {
        var deleteCount = accountDao.deleteByUserId(10);
        assertEquals(0, deleteCount);

        var list = accountDao.findAll();

        // 件数のチェック(全てのレコードが消えていない事を確認)
        assertEquals(2, list.size());

    }
}
