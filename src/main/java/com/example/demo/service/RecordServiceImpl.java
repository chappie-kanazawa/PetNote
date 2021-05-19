package com.example.demo.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Record;
import com.example.demo.repository.RecordDao;

@Service
public class RecordServiceImpl implements RecordService {

	private final RecordDao dao;

	public RecordServiceImpl(RecordDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Record> findAll() {
		return dao.findAll();
	}
	
	//成長記録の取得
	@Override
	public Record findByRecId(int recId){
		//Optional<Pet>一件を取得 idが無ければ例外発生　
		try {
			return dao.findByRecId(recId);
		} catch (EmptyResultDataAccessException e) {
			throw new RecordNotFoundException("指定された成長記録が存在しません");
		}
	}
	
	//ログイン中のユーザーのあるペットの全成長記録の取得
	@Override
	public List<Record> findByPetId(int petId){
		//あるユーザーのOptional<Record>全件を取得 idが無ければ例外発生　
		try {
			return dao.findByPetId(petId);
		} catch (EmptyResultDataAccessException e) {
			throw new RecordNotFoundException("指定された成長記録が存在しません");
		}
	}
	
	//ログイン中のユーザーの全ペットの全成長記録の取得
	@Override
	public List<Record> findByUserId(int userId){
		//あるユーザーのOptional<Record>全件を取得 idが無ければ例外発生　
		try {
			return dao.findByUserId(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new RecordNotFoundException("指定された成長記録が存在しません");
		}
	}

	//成長記録投稿
	@Override
	public void postRecord(Record record) {
		dao.insert(record);
	}
	
	//成長記録の編集
	@Override
	public void editRecord(Record record) {

		//Recordを更新　idが無ければ例外発生
		if(dao.update(record) == 0) {
			throw new RecordEditErrorException("成長記録の編集に失敗しました");
		}
	}
	
	//成長記録1件の削除
	@Override
	public void deleteByRecId(int recId) {
		//Recordを削除 rec_idがなければ例外発生
		if(dao.deleteByRecId(recId) == 0) {
			throw new RecordEditErrorException("削除する成長記録が存在しません");
		} 
	}
	
	//あるペットの全成長記録を削除
	@Override
	public void deleteByPetId(int petId) {

		//Recordを削除 pet_idがなければ例外発生
		if(dao.deleteByPetId(petId) == 0) {
			throw new RecordEditErrorException("削除する成長記録が存在しません");
		} 
	}
	
	//アカウント削除に伴う成長記録の削除
	@Override
	public void deleteByUserId(int userId) {

		//Recordを削除 user_idがなければ例外発生
		if(dao.deleteByUserId(userId) == 0) {
			throw new RecordEditErrorException("削除する成長記録が存在しません");
		} 
	}
}
