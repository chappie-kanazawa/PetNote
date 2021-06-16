package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Record;

public interface RecordRepositoryCustom {
	
	List<Record> findAll();
	
	Record findByRecId(int recId);
	
	List<Record> findByPetId(int petId);
	
	List<Record> findByUserId(int userId);

	void insert(Record record);

	int update(Record record);
	
	int deleteByRecId(int recId);	

	int deleteByPetId(int petId);
	
	int deleteByUserId(int userId);

	Page<Record> findByPetIdByOrderByRecDate(int petId, Pageable pageable);

}
