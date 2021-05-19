package com.example.demo.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pet;
import com.example.demo.repository.PetDao;

@Service
public class PetServiceImpl implements PetService {

	private final PetDao dao;

	public PetServiceImpl(PetDao dao) {
		this.dao = dao;
	}
	
	//ペット情報の取得
	@Override
	public List<Pet> findAll(){
		return dao.findAll();
	}
	
	//ペット情報の取得
	@Override
	public Pet findByPetId(int petId){
		//Optional<Pet>一件を取得 idが無ければ例外発生　
		try {
			return dao.findByPetId(petId);
		} catch (EmptyResultDataAccessException e) {
			throw new PetInfoErrorException("指定されたペット情報が存在しません");
		}
	}
	
	//ログイン中のユーザーの全ペット情報の取得
	@Override
	public List<Pet> findByUserId(int userId){
		//あるユーザーのOptional<Pet>全件を取得 idが無ければ例外発生　
		try {
			return dao.findByUserId(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new PetInfoErrorException("ペット情報の取得に失敗しました");
		}
	}
	
	//ログイン中のユーザーのある種類の全ペット情報の取得
	@Override
	public List<Pet> findByUserIdAndKind(int userId, String kind){
		//あるユーザーのある種類のOptional<Pet>全件を取得 idが無ければ例外発生　
		try {
			return dao.findByUserIdAndKind(userId, kind);
		} catch (EmptyResultDataAccessException e) {
			throw new PetInfoErrorException("ペット情報の取得に失敗しました");
		}
	}

	//ペット追加登録
	@Override
	public void addPet(Pet pet) {
		dao.insert(pet);
	}
	
	//ペット情報の更新
	@Override
	public void editPet(Pet pet) {

		//Petを更新　idが無ければ例外発生
		if(dao.update(pet) == 0) {
			throw new PetEditErrorException("更新するペット情報が存在しません");
		}
	}

	//ペット情報の削除
	@Override
	public void deleteByPetId(int petId) {

		//Petを削除 idがなければ例外発生
		if(dao.deleteByPetId(petId) == 0) {
			throw new PetEditErrorException("削除するペット情報が存在しません");
		}
	}
	
	//アカウント削除に伴うペット情報の削除
	@Override
	public void deleteByUserId(int userId) {

		//Petを削除 user_idがなければ例外発生
		if(dao.deleteByUserId(userId) == 0) {
			throw new PetEditErrorException("削除するペット情報が存在しません");
		}
	}

}
