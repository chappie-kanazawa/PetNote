//package com.example.demo.service;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//// Nakano to Junit5
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.example.demo.entity.Account;
//import com.example.demo.entity.Login;
//import com.example.demo.repository.AccountDao;
//
////import lombok.var;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("LoginServiceImplの単体テスト")
//class LoginServiceImplUnitTest {
//
//	
//    @Mock // モック(stub)クラス ダミーオブジェクト
//    private AccountDao dao;
//    
//    @InjectMocks // テスト対象クラス　モックを探す newする
//    private LoginServiceImpl loginServiceImpl; 
//
////	@BeforeEach
////    void setup() {
////		// 各テストの実行前にモックオブジェクトを初期化する
////        MockitoAnnotations.initMocks(this);
////    }
//
//    
//    @Test // テストケース
//    @DisplayName("ユーザー名とパスワードが正しい場合")
//	void testExecute1() {
//    	Login login1 = new Login("taro", "1111");
//    	boolean result1 = loginServiceImpl.execute(login1);
//
//    	//テスト用ダミーデータlogin1に値が入っているか確認
//    	assertEquals("taro", login1.getUserName());
//    	assertEquals("1111", login1.getPass());
//
//    	//ログイン認証結果がOKになっているか確認
//    	assertTrue(result1);
//   
//	}
//    
//    @Test // テストケース
//    @DisplayName("ユーザー名は存在するがパスワードが間違っている場合")
//	void testExecute2() {
//    	Login login2 = new Login("taro", "1111xxxx");
//		boolean result2 = loginServiceImpl.execute(login2);
//		
//		//返り値がfalseであるか判定
//		assertFalse(result2);
//
//	}
//    
//    @Test
//    @DisplayName("ユーザー名が存在しない場合")
//    void testExecute3() {
//    	Login login3 = new Login("taroxxxx", "1111xxxx");
//    	boolean result3 = loginServiceImpl.execute(login3);
//        
//        //返り値がfalseであるか判定
//        assertFalse(result3);
//    }
//    
//    @Test
//    @DisplayName("ログインしたユーザー名の情報を取得")
//    void testFindByLogin() {
//    	Login login4 = new Login("taro", "1111");
//      
//    	assertEquals("taro", login4.getUserName());
//    	assertEquals("1111", login4.getPass());
//    	
//    	Account account4 = new Account();
//    	account4 = loginServiceImpl.findByUserName(login4.getUserName());
//
//    	assertEquals(null, account4);
//    	
////    	assertEquals("taro", account4.getUserName());
////    	assertEquals("1111", account4.getPass());
////    	assertEquals("xxxx", account4.getIcon());
////    	assertEquals("よろしく", account4.getIntro());
////    	assertEquals(1, account4.getUserId());
//    	
//    }
//}
