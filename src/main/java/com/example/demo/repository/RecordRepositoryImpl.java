package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.Record;

public class RecordRepositoryImpl implements RecordRepositoryCustom{
	
	private final JdbcTemplate jdbcTemplate;

	public RecordRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Record> findAll() {

		String sql = "SELECT rec_id, comment, rec_pic, rec_date, pet_id "
				+ "FROM record";

		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		//return用の空のListを用意
		List<Record> list = new ArrayList<>();

		//二つのテーブルのデータをTaskにまとめる
		for(Map<String, Object> result : resultList) {

			Record record = new Record();
			record.setComment((String)result.get("comment"));
			record.setRecPic((String)result.get("rec_pic"));
			record.setRecDate(((Timestamp) result.get("rec_date")).toLocalDateTime());
			record.setPetId((int)result.get("pet_id"));

			list.add(record);
		}
		return list;
	}

	//1件の成長記録を見つけてくる
	@Override
	public Record findByRecId(int recId) {
		//指定したrec_idと一致する成長記録を取得するためのSQLを記述する
		String sql = "SELECT rec_id, comment, rec_pic, rec_date, pet_id "
				+ "FROM record "
				+ "WHERE rec_id = ?";

		Map<String, Object> result = jdbcTemplate.queryForMap(sql, recId);

		Record record = new Record();
		record.setRecId((int)result.get("rec_id"));
		record.setComment((String)result.get("comment"));
		record.setRecPic((String)result.get("rec_pic"));
		record.setRecDate(((Timestamp) result.get("rec_date")).toLocalDateTime());
		record.setPetId((int)result.get("pet_id"));

		return record;
	}

	//あるペットの全記録を見つけてくる
	@Override
	public List<Record> findByPetId(int petId) {
		String sql = "SELECT rec_id, comment, rec_pic, rec_date, pet_id "
				+ "FROM record "
				+ "WHERE pet_id = ?";

		//SQLとpetIdを渡し、記録一覧をMapのListで取得する
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, petId);

		//return用の空のListを用意
		List<Record> list = new ArrayList<>();

		//二つのテーブルのデータをRecordにまとめる
		for(Map<String, Object> result : resultList) {

			Record record = new Record();
			record.setRecId((int)result.get("rec_id"));
			record.setComment((String)result.get("comment"));
			record.setRecPic((String)result.get("rec_pic"));
			record.setRecDate(((Timestamp) result.get("rec_date")).toLocalDateTime());
			record.setPetId((int)result.get("pet_id"));

			list.add(record);
		}
		return list;
	}

	//あるユーザーの全記録を見つけてくる
	@Override
	public List<Record> findByUserId(int userId) {
		String sql = "SELECT rec_id, comment, rec_pic, rec_date, pet_id "
				+ "FROM record "
				+ "WHERE pet_id IN ( "
						+ "SELECT pet.pet_id "
						+ "FROM pet "
						+ "WHERE pet.user_id = ? "
					+ ")";

		//SQLとpetIdを渡し、記録一覧をMapのListで取得する
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, userId);

		//return用の空のListを用意
		List<Record> list = new ArrayList<>();

		//二つのテーブルのデータをRecordにまとめる
		for(Map<String, Object> result : resultList) {

			Record record = new Record();
			record.setRecId((int)result.get("rec_id"));
			record.setComment((String)result.get("comment"));
			record.setRecPic((String)result.get("rec_pic"));
			record.setRecDate(((Timestamp) result.get("rec_date")).toLocalDateTime());
			record.setPetId((int)result.get("pet_id"));

			list.add(record);
		}
		return list;
	}


	//1件の成長記録を追加
	@Override
	public void insert(Record record) {
		jdbcTemplate.update("INSERT INTO record(comment, rec_pic, rec_date, pet_id) VALUES(?, ?, ?, ?)",
				record.getComment(), record.getRecPic(), record.getRecDate(), record.getPetId());
	}

	//1件の成長記録を更新
	@Override
	public int update(Record record) {
		return jdbcTemplate.update("UPDATE record SET comment = ?, rec_pic = ?, rec_date = ?, pet_id = ? WHERE rec_id = ?",
				record.getComment(), record.getRecPic(), record.getRecDate(), record.getPetId(), record.getRecId() );
	}

	//1件の成長記録を削除
	@Override
	public int deleteByRecId(int recId) {
		return jdbcTemplate.update("DELETE FROM record WHERE rec_id = ?", recId);
	}

	//あるペットの成長記録を削除
	@Override
	public int deleteByPetId(int petId) {
		//指定したpet_idと一致するrec_idを削除するためのSQLを記述する
		String sql = "DELETE "
				+ "FROM record "
				+ "WHERE pet_id = ?";

		return jdbcTemplate.update(sql, petId);
	}

	//あるユーザーの成長記録を削除
	@Override
	public int deleteByUserId(int userId) {
		//指定したuser_idと一致するrec_idを削除するためのSQLを記述する
		String sql = "DELETE "
				+ "FROM record "
				+ "WHERE pet_id IN ( "
						+ "SELECT pet.pet_id "
						+ "FROM pet "
						+ "WHERE pet.user_id = ? "
					+ ")";

		return jdbcTemplate.update(sql, userId);
	}
	
	//日付順にならべたpetIdに紐づくrecordをリストで取得
	@Override
	public Page<Record> findByPetIdByOrderByRecDate(int petId, Pageable pageable){
		String sql = "SELECT rec_id, comment, rec_pic, rec_date, pet_id "
				+ "FROM record "
				+ "WHERE pet_id = ?"
				+ "ORDER BY rec_date DESC";

		//SQLとpetIdを渡し、記録一覧をMapのListで取得する
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, petId);

		//return用の空のListを用意
		List<Record> list = new ArrayList<>();

		//二つのテーブルのデータをRecordにまとめる
		for(Map<String, Object> result : resultList) {

			Record record = new Record();
			record.setRecId((int)result.get("rec_id"));
			record.setComment((String)result.get("comment"));
			record.setRecPic((String)result.get("rec_pic"));
			record.setRecDate(((Timestamp) result.get("rec_date")).toLocalDateTime());
			record.setPetId((int)result.get("pet_id"));

			list.add(record);
		}
		
		Page<Record> recListPage = new PageImpl<Record>(list, pageable, list.size());

		return recListPage;	
	}
	

}