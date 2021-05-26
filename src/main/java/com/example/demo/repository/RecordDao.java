package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer> {
	
	List<Record> findAll();

	Record findByRecId(int recId);
	
	List<Record> findByPetId(int petId);
	
	List<Record> findByUserId(int userId);

	void insert(Record record);

	int update(Record record);
	
	int deleteByRecId(int recId);	

	int deleteByPetId(int petId);
	
	int deleteByUserId(int userId);
	
	public Page<Record> findAllWithPaging(Pageable pageable);
	
}
