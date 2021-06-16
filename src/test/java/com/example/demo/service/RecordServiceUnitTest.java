package com.example.demo.service;

// Nakano to Junit5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.example.demo.entity.Record;
import com.example.demo.repository.RecordRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("RecordServiceUnitImplの単体テスト")
class RecordServiceUnitTest {

	@Mock // モック(stub)クラス ダミーオブジェクト
	private RecordRepository dao;

	@InjectMocks // テスト対象クラス モックを探す newする
	private RecordServiceImpl recordServiceImpl;

	@Test // テストケース
	@DisplayName("成長記録1件の取得(正常系)")
	// テスト名
	void testGetRecordReturnOne() {

		// Recordをデフォルト値でインスタンス化
		Record record = new Record();
		// Optional<Task> taskOpt = Optional.ofNullable(task);
		// モッククラスのI/Oをセット
		when(dao.findByRecId(1)).thenReturn(record);

		// サービスを実行
		Record recActual = recordServiceImpl.findByRecId(1);

		// モックの指定メソッドの実行回数を検査
		verify(dao, times(1)).findByRecId(1);

		// 存在を確かめるため(isPresent()を使うため）、petActualをOptionalでラップ
		Optional<Record> recordActualOpt = Optional.ofNullable(recActual);

		// Petが存在していることを確認
		assertTrue(recordActualOpt.isPresent());
	}

	@Test // テストケース
	@DisplayName("成長記録1件の取得(異常系)")
	// テスト名
	void testFindByRecIdThrowException() {

		// モッククラスのI/Oをセット
		when(dao.findByRecId(10)).thenThrow(new EmptyResultDataAccessException(1));

		// 成長記録が得できないとRecordNotFoundExceptionが発生することを検査
		try {
			recordServiceImpl.findByRecId(10);
		} catch (RecordNotFoundException e) {
			assertEquals(e.getMessage(), "指定された成長記録が存在しません");
		}
	}

	@Test // テストケース
	@DisplayName("あるペットの全成長記録の取得(正常系)")
	// テスト名
	void testFindByPetIdReturnList() {

		// モックから返すListに3つのPetオブジェクトをセット
		List<Record> list = new ArrayList<>();
		Record rec1 = new Record();
		Record rec2 = new Record();
		list.add(rec1);
		list.add(rec2);

		// モッククラスのI/Oをセット（findByUserId()の型と異なる戻り値はNG）
		when(dao.findByPetId(1)).thenReturn(list);

		// サービスを実行
		List<Record> actualList = recordServiceImpl.findByPetId(1);

		// モックの指定メソッドの実行回数を検査
		verify(dao, times(1)).findByPetId(1);

		// 戻り値の検査(expected, actual)
		assertEquals(2, actualList.size());
	}

	@Test // テストケース
	@DisplayName("あるペットの全成長記録の取得(異常系)")
	// テスト名
	void testFindByPetIdThrowException() {

		// モッククラスのI/Oをセット
		when(dao.findByPetId(10)).thenThrow(new EmptyResultDataAccessException(1));

		// 成長記録が取得できないとRecordNotFoundExceptionが発生することを検査
		try {
			recordServiceImpl.findByPetId(10);
		} catch (RecordNotFoundException e) {
			assertEquals(e.getMessage(), "指定された成長記録が存在しません");
		}
	}
	
	@Test // テストケース
	@DisplayName("あるユーザーの全成長記録の取得(正常系)")
	// テスト名
	void testFindByUserIdReturnList() {

		// モックから返すListに3つのPetオブジェクトをセット
		List<Record> list = new ArrayList<>();
		Record rec1 = new Record();
		Record rec2 = new Record();
		list.add(rec1);
		list.add(rec2);

		// モッククラスのI/Oをセット（findByUserId()の型と異なる戻り値はNG）
		when(dao.findByUserId(1)).thenReturn(list);

		// サービスを実行
		List<Record> actualList = recordServiceImpl.findByUserId(1);

		// モックの指定メソッドの実行回数を検査
		verify(dao, times(1)).findByUserId(1);

		// 戻り値の検査(expected, actual)
		assertEquals(2, actualList.size());
	}

	@Test // テストケース 単体テストではデータベースの例外は考えない
	@DisplayName("存在しないidの場合メソッドが実行されないことを確認するテスト")
	// テスト名
	void throwNotFoundException() {

		// モッククラスのI/Oをセット
		when(dao.deleteByRecId(0)).thenReturn(0);

		// 削除対象が存在しない場合、例外が発生することを検査
		try {
			recordServiceImpl.deleteByRecId(0);
		} catch (RecordEditErrorException e) {
			assertEquals(e.getMessage(), "削除する成長記録が存在しません");
		}
	}

}
