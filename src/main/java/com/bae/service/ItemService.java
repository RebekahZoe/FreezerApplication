package com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;

@Service
public class ItemService {
	
	private ItemsRepo repo;
	
	private FreezerRepo freezerRepo;
	
	
	public ItemService(ItemsRepo repo) {
		super();
		this.repo = repo;
	}  

	public List<Items> readItems() {
		return this.repo.findAll();
	}
 
	public Items createItem(Items item) {
		return this.repo.save(item);  
	}

	public boolean deleteItem(Long id) throws ItemDoesntexistException {
		if(!this.repo.existsById(id)) {
			throw new ItemDoesntexistException();
		}
		this.repo.deleteById(id); 
		return this.repo.existsById(id); 
	}
	
	public boolean  deleteItem(String name) throws ItemDoesntexistException {
		return this.deleteItem(this.repo.findByItemName(name).getId()); 
	}
	
	public Items findItemByID(Long id) throws ItemDoesntexistException {
		return this.repo.findById(id).orElseThrow(
				() -> new ItemDoesntexistException());
	}
	
//	public void deleteAllItemsInAFreezer(Freezers freezers) {
//		List<Items> items = this.repo.findAll();
//		for (Items item : items) {
//			if (item.getFreezerId() == freezers.getId()) {
//				this.repo.deleteById(item.getId());
//			}
//		}
//	}
//	
//	public void deleteAllItemsInAFreezer(String name) {
//		this.deleteAllItemsInAFreezer(this.freezerRepo.findByFreezerName(name));
//	}
	
	public Items updateItem(Items item, Long id) throws ItemDoesntexistException {
		Items toUpdate = findItemByID(id);
		toUpdate.setItemName(item.getItemName());
		toUpdate.setQuantity(item.getQuantity());
		return this.repo.save(toUpdate); 
	}
	public Items updateItem(Items item, String name) throws ItemDoesntexistException {
		return this.updateItem(item, this.repo.findByItemName(name).getId()); 
	}
}
