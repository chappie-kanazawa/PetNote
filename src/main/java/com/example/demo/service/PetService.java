package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Pet;

public interface PetService {
	
	List<Pet> findAll();

	Pet findByPetId(int petId);
	
	List<Pet> findByUserId(int userId);
	
	List<Pet> findByUserIdAndKind(int userId, String kind);

	void addPet(Pet pet);

	void editPet(Pet pet);
	
	void deleteByPetId(int petId);	

	void deleteByUserId(int userId);	
	
	
}
