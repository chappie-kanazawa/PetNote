package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pet;



@Repository
public class PetDaoImpl implements PetDao {

	private final JdbcTemplate jdbcTemplate;

	public PetDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Pet> findAll() {

		String sql = "SELECT pet.pet_id, pet_name, kind, gender, pet_icon, user_id "
				+ "FROM pet ";

		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		//return用の空のListを用意
		List<Pet> list = new ArrayList<>();

		//二つのテーブルのデータをTaskにまとめる
		for(Map<String, Object> result : resultList) {

			Pet pet = new Pet();
			pet.setPetId((int)result.get("pet_id"));
			pet.setPetName((String)result.get("pet_name"));
			pet.setKind((String)result.get("kind"));
			pet.setGender((int)result.get("gender"));
			pet.setPetIcon((String)result.get("pet_icon"));
			pet.setUserId((int)result.get("user_id"));

			list.add(pet);
		}
		return list;
	}

	//1件のペット情報を見つけてくる
	@Override
	public Pet findByPetId(int petId) {
		String sql = "SELECT pet.pet_id, pet_name, kind, gender, pet_icon, user_id "
				+ "FROM pet "
				+ "WHERE pet.pet_id = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, petId);
		
		Pet pet = new Pet();
		pet.setPetId((int)result.get("pet_id"));
		pet.setPetName((String)result.get("pet_name"));
		pet.setKind((String)result.get("kind"));
		pet.setGender((int)result.get("gender"));
		pet.setPetIcon((String)result.get("pet_icon"));
		pet.setUserId((int)result.get("user_id"));
		
		return pet;
	}
	
	//あるユーザーの全ペットを見つけてくる
	@Override
	public List<Pet> findByUserId(int userId) {
		//指定したuser_idと一致するペット情報を取得するためのSQLを記述する
		String sql = "SELECT pet_id, pet_name, kind, gender, pet_icon, user_id "
				+ "FROM pet "
				+ "WHERE user_id = ?";

		//SQLとuserIdを渡し、ペット一覧をMapのListで取得する
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, userId);

		//return用の空のListを用意
		List<Pet> list = new ArrayList<>();

		//二つのテーブルのデータをPetにまとめる
		for(Map<String, Object> result : resultList) {

			Pet pet = new Pet();
			pet.setPetId((int)result.get("pet_id"));
			pet.setPetName((String)result.get("pet_name"));
			pet.setKind((String)result.get("kind"));
			pet.setGender((int)result.get("gender"));
			pet.setPetIcon((String)result.get("pet_icon"));
			pet.setUserId((int)result.get("user_id"));

			list.add(pet);
		}
		return list;
	}

	//ログインしているユーザーの、ある種類の全ペットを見つけてくる
	@Override
	public List<Pet> findByUserIdAndKind(int userId, String kind) {
		//指定したuser_idと一致するペットのリストを取得するためのSQLを記述する
		String sql = "SELECT pet_id, pet_name, kind, gender, pet_icon, user_id "
				+ "FROM pet "
				+ "WHERE user_id = ? AND kind = ?";

		//SQLとuserIdを渡し、ペット一覧をMapのListで取得する
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, userId, kind);

		//return用の空のListを用意
		List<Pet> list = new ArrayList<>();

		//二つのテーブルのデータをPetにまとめる
		for(Map<String, Object> result : resultList) {

			Pet pet = new Pet();
			pet.setPetId((int)result.get("pet_id"));
			pet.setPetName((String)result.get("pet_name"));
			pet.setKind((String)result.get("kind"));
			pet.setGender((int)result.get("gender"));
			pet.setPetIcon((String)result.get("pet_icon"));
			pet.setUserId((int)result.get("user_id"));

			list.add(pet);
		}
		return list;
	}

	
	//1件のペットを追加
	@Override
	public void insert(Pet pet) {
		jdbcTemplate.update("INSERT INTO pet(pet_name, kind, gender, pet_icon, user_id) VALUES(?, ?, ?, ?, ? )",
				pet.getPetName(), pet.getKind(), pet.getGender(), pet.getPetIcon(), pet.getUserId() );
	}

	//1件のペットの情報を更新
	@Override
	public int update(Pet pet) {
		return jdbcTemplate.update("UPDATE pet SET pet_name = ?, kind = ?, gender = ?, pet_icon = ?, user_id = ? WHERE pet_id = ?",
				pet.getPetName(), pet.getKind(), pet.getGender(), pet.getPetIcon(), pet.getUserId(), pet.getPetId() );
	}

	//1件のペット情報を削除
	@Override
	public int deleteByPetId(int petId) {
		return jdbcTemplate.update("DELETE FROM pet WHERE pet_id = ?", petId);
	}

	//ログインしているユーザーの全ペット情報を削除
	@Override
	public int deleteByUserId(int userId) {
		//指定したuser_idと一致するpet_idを削除するためのSQLを記述する
//		String sql = "DELETE "
//				+ "FROM pet "
//				+ "WHERE pet_id IN ( "
//					+ "SELECT pet_id "
//					+ "FROM p_a_relation "
//					+ "WHERE p_a_relation.user_id = ?"
//					+ ")";
//		
//		return jdbcTemplate.update(sql, userId);
		String sql = "DELETE FROM pet where user_id = ?";
		return jdbcTemplate.update(sql, userId);
		
	}
	
}
