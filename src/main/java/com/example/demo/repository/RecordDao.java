package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Record;

public interface RecordDao {
	
	List<Record> findAll();

	Record findByRecId(int recId);
	
	List<Record> findByPetId(int petId);
	
	List<Record> findByUserId(int userId);

	void insert(Record record);

	int update(Record record);
	
	int deleteByRecId(int recId);	

	int deleteByPetId(int petId);
	
	int deleteByUserId(int userId);

}
