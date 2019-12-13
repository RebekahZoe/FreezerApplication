package com.bae.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.repo.FreezerRepo;

@Service
public class FreezerService {
	
	private FreezerRepo repo;
	
	public List<Freezers> readFreezers(){
		return this.repo.findAll();
	}
	
	public Freezers createFreezer(Freezers freezer){
		return this.repo.save(freezer);
	}
	public boolean  deleteFreezer(Long id) throws FreezerDoesntexistException {
		if(!this.repo.existsById(id)) {
			throw new FreezerDoesntexistException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
	

}
