package com.bae.service;

import java.util.List;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;

public class ItemService {
	
	public ItemsRepo repo;
	
	public List<Items> readItems() {
		return this.repo.findAll();
	}
 
	public Items createItem(Items item) {
		return this.repo.save(item);  
	}

	public boolean deleteItem(Long id) throws itemDoesntexistException {
		if(!this.repo.existsById(id)) {
			throw new itemDoesntexistException();
		}
		this.repo.deleteById(id); 
		return this.repo.existsById(id); 
	}
	public Items findItemByID(Long id) throws itemDoesntexistException {
		return this.repo.findById(id).orElseThrow(
				() -> new itemDoesntexistException());
	}

	public Items updateItem(Items item, Long id) throws itemDoesntexistException {
		Items toUpdate = findItemByID(id);
		toUpdate.setItemName(item.getItemName());
		toUpdate.setQuantity(item.getQuantity());
		return this.repo.save(toUpdate); 
	}

}
