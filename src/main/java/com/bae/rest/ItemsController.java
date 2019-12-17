package com.bae.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Items;
import com.bae.service.ItemService;
import com.bae.service.itemDoesntexistException;

@RestController
public class ItemsController {
	
private ItemService service;

	@Autowired
	public ItemsController(ItemService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createItems")
	public Items createItems(@RequestBody Items items) {
		return this.service.createItem(items);
	}

	@DeleteMapping("/deleteItem/{id}")
	public void deleteItem(@PathVariable Long id) throws itemDoesntexistException {
		this.service.deleteItem(id);
	}
	
	@GetMapping("/getItem/{id}")
	public Items getItem(@PathVariable Long id) throws  itemDoesntexistException {
		return this.service.findItemByID(id);
	}

	@GetMapping("/getAllItems")
	public List<Items> getAllItems() {
		return this.service.readItems();
	}
	
	@PutMapping("/updateItem/{id}")
	public Items updateItem(@PathParam("id") Long id, @RequestBody Items item) throws itemDoesntexistException {
		return this.service.updateItem(item, id);
	}
	
	
}