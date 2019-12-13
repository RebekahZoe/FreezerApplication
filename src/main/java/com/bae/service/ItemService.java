package com.bae.service;

import java.util.List;

import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;

public class ItemService {
	
	public ItemsRepo repo;
	
	public List<Items> readItems() {
		return this.repo.findAll();
	}
 
	public Object createItem(Items item) {
		return this.repo.save(item);  
	}

	public void deleteItem(Long id) {
		this.repo.deleteById(id);
		
	}

}
