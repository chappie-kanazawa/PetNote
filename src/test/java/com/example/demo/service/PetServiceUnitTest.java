package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*; // Nakano to Junit5
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.entity.Pet;
import com.example.demo.repository.PetDao;

@ExtendWith(MockitoExtension.class)
@DisplayName("PetServiceUnitImplの単体テスト")
class PetServiceUnitTest {

    @Mock // モック(stub)クラス ダミーオブジェクト
    private PetDao dao;

    @InjectMocks // テスト対象クラス　モックを探す newする
    private PetServiceImpl petServiceImpl; 
    
    @Test // テストケース
    @DisplayName("テーブルpetの全件取得で0件の場合のテスト")
        // テスト名
    void petFindAllReturnEmptyList() {

    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Pet> list = new ArrayList<>();

        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(dao.findAll()).thenReturn(list);

        // サービスを実行
        List<Pet> actualList= petServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(0, actualList.size());
    }
    
    @Test // テストケース
    @DisplayName("テーブルPetの全件取得で2件の場合のテスト")
        // テスト名
    void testFindAllReturnList() {

    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Pet> list = new ArrayList<>();
    	Pet pet1 = new Pet();
    	Pet pet2 = new Pet();
    	list.add(pet1);
    	list.add(pet2);

        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(dao.findAll()).thenReturn(list);

        // サービスを実行
        List<Pet> actualList= petServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(2, actualList.size());
    }
    
    @Test // テストケース
    @DisplayName("ペット情報1件の取得(正常系)")
        // テスト名
    void testGetPetReturnOne() {

    	//Petをデフォルト値でインスタンス化
    	Pet pet = new Pet();
    	//Optional<Task> taskOpt  = Optional.ofNullable(task);
        // モッククラスのI/Oをセット
        when(dao.findByPetId(1)).thenReturn(pet);

        // サービスを実行
        Pet petActual = petServiceImpl.findByPetId(1);

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findByPetId(1);

        //存在を確かめるため(isPresent()を使うため）、petActualをOptionalでラップ
        Optional<Pet> petActualOpt = Optional.ofNullable(petActual);
        
        //Petが存在していることを確認
        assertTrue(petActualOpt.isPresent());
    }

    
    @Test // テストケース
    @DisplayName("ペット情報1件の取得(異常系)")
        // テスト名
    void testFindByPetIdThrowException() {

        // モッククラスのI/Oをセット
        when(dao.findByPetId(10)).thenThrow(new EmptyResultDataAccessException(1));

      //タスクが取得できないとTaskNotFoundExceptionが発生することを検査
        try {
        	petServiceImpl.findByPetId(10);
        } catch (PetInfoErrorException e) {
        	assertEquals(e.getMessage(), "指定されたペット情報が存在しません");
        }
    }
    
    @Test // テストケース
    @DisplayName("あるユーザーの全ペット情報の取得(正常系)")
        // テスト名
    void testFindByUserIdReturnList() {

    	//モックから返すListに3つのPetオブジェクトをセット
    	List<Pet> list = new ArrayList<>();
    	Pet pet1 = new Pet();
    	Pet pet2 = new Pet();
    	Pet pet3 = new Pet();
    	list.add(pet1);
    	list.add(pet2);
    	list.add(pet3);

        // モッククラスのI/Oをセット（findByUserId()の型と異なる戻り値はNG）
        when(dao.findByUserId(1)).thenReturn(list);

        // サービスを実行
        List<Pet> actualList= petServiceImpl.findByUserId(1);

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findByUserId(1);

        // 戻り値の検査(expected, actual)
        assertEquals(3, actualList.size());
    }

    @Test // テストケース
    @DisplayName("あるユーザーの全ペット情報の取得(異常系)")
        // テスト名
    void testFindByUserIdThrowException() {

        // モッククラスのI/Oをセット
        when(dao.findByUserId(10)).thenThrow(new EmptyResultDataAccessException(1));

      //ペットが取得できないとPetInfoErrorExceptionが発生することを検査
        try {
        	petServiceImpl.findByUserId(10);
        } catch (PetInfoErrorException e) {
        	assertEquals(e.getMessage(), "ペット情報の取得に失敗しました");
        }
    } 
    
    
    @Test // テストケース
    @DisplayName("あるユーザーのある種類の全ペット情報(2件)の取得(正常系)")
        // テスト名
    void testFindByUserIdAndKindReturnList() {

    	//モックから返すListに2つのPetオブジェクトをセット
    	List<Pet> list = new ArrayList<>();
    	Pet pet1 = new Pet();
    	Pet pet2 = new Pet();
    	list.add(pet1);
    	list.add(pet2);

        // モッククラスのI/Oをセット（findByUserId()の型と異なる戻り値はNG）
        when(dao.findByUserIdAndKind(1, "犬")).thenReturn(list);

        // サービスを実行
        List<Pet> actualList= petServiceImpl.findByUserIdAndKind(1, "犬");

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findByUserIdAndKind(1, "犬");

        // 戻り値の検査(expected, actual)
        assertEquals(2, actualList.size());
    }
    
    @Test // テストケース
    @DisplayName("あるユーザーのある種類の全ペット情報(2件)の取得(異常系)")
    void testFindByUserIdandKindThrowException() {  
    	
	    // モッククラスのI/Oをセット
	    when(dao.findByUserIdAndKind(10, "犬")).thenThrow(new EmptyResultDataAccessException(1));
	
	  //ペットが取得できないとPetInfoErrorExceptionが発生することを検査
	    try {
	    	petServiceImpl.findByUserIdAndKind(10, "犬");
	    } catch (PetInfoErrorException e) {
	    	assertEquals(e.getMessage(), "ペット情報の取得に失敗しました");
	    }
	}
    
    @Test // テストケース　単体テストではデータベースの例外は考えない
    @DisplayName("存在しないidの場合メソッドが実行されないことを確認するテスト")
        // テスト名
    void throwNotFoundException() {

        // モッククラスのI/Oをセット
        when(dao.deleteByUserId(0)).thenReturn(0);

      //削除対象が存在しない場合、例外が発生することを検査
        try {
        	petServiceImpl.deleteByPetId(0);
        } catch (PetEditErrorException e) {
        	assertEquals(e.getMessage(), "削除するペット情報が存在しません");
        }
    }
    
}
