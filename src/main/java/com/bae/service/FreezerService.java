package com.bae.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;

@Service
public class FreezerService {
	
	private FreezerRepo repo;
	
	private ItemService itemService;
	public FreezerService(FreezerRepo repo,ItemService itemService,ItemsRepo itemRepo) {
		super();
		this.repo = repo;
		this.itemService= itemService;
	} 
	public List<Freezers> readFreezers(){
		return this.repo.findAll();
	}
	
	public Freezers createFreezer(Freezers freezer){
		return this.repo.save(freezer);
	}
	
	public Freezers findFreezerByID(Long id) throws FreezerDoesntexistException {
		return this.repo.findById(id).orElseThrow(
				() -> new FreezerDoesntexistException());
	} 

	public boolean  deleteFreezer(Long id) throws FreezerDoesntexistException {
		if(!this.repo.existsById(id)) {
			throw new FreezerDoesntexistException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id); 
	}
	
	public Freezers addItemToFreezer(Long id, Items item) throws FreezerDoesntexistException {
		Freezers toUpdate = findFreezerByID(id);
		Items newItem = this.itemService.createItem(item);
		toUpdate.getItems().add(newItem);
		return this.repo.saveAndFlush(toUpdate);
		}

	public Set<Items> getItemsFromFreezer(Long id) throws FreezerDoesntexistException {
		Freezers toDisplay = findFreezerByID(id);
		return toDisplay.getItems();
	}
	
	

}
