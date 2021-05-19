package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Pet;

public interface PetDao {
	
	List<Pet> findAll();

	Pet findByPetId(int petId);
	
	List<Pet> findByUserId(int userId);
	
	List<Pet> findByUserIdAndKind(int userId, String kind);

	void insert(Pet pet);

	int update(Pet pet);
	
	int deleteByPetId(int petId);	

	int deleteByUserId(int userId);	
	
}
