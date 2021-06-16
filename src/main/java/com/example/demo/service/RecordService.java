package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Record;

public interface RecordService  {
	
	List<Record> findAll();

	Record findByRecId(int recId);
	
	List<Record> findByPetId(int recId);
	
	List<Record> findByUserId(int userId);

	void postRecord(Record record);

	void editRecord(Record record);
	
	void deleteByRecId(int recId);	

	void deleteByPetId(int petId);
	
	void deleteByUserId(int userId);
	
	Page<Record> findByPetIdByOrderByRecDate(int petId, Pageable pageable);
	
}
