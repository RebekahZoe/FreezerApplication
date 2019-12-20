package com.bae.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.FreezerService;

@RestController
public class FreezerController {
	
	private FreezerService service;
	
	@Autowired
	public FreezerController(FreezerService service) {
		super();
		this.service = service;
	}  
	
	@PostMapping("/createFreezer")
	public Freezers createFreezer(@RequestBody Freezers freezer) {
		return this.service.createFreezer(freezer); 
	}

	@DeleteMapping("/deleteFreezer/{id}")
	public void deleteFreezer(@PathVariable Long id) throws FreezerDoesntexistException {
		this.service.deleteFreezer(id);
	}
	
	@GetMapping("/getFreezer/{id}")
	public Freezers getFreezer(@PathVariable Long id) throws FreezerDoesntexistException {
		return this.service.findFreezerByID(id);
	}

	@GetMapping("/getAllFreezers")
	public List<Freezers> getAllFreezers() {
		return this.service.readFreezers();
	}
	@PatchMapping("/addItem/{id}")
	public Freezers addItemToFreezer(@PathVariable Long id,@RequestBody Items item) throws FreezerDoesntexistException {
		return this.service.addItemToFreezer(id, item);
	}
	@GetMapping("/getItemsFromFreezer/{id}")
	public Set<Items> getItemsFromFreezer(@PathVariable Long id){
		return this.service.getItemsFromFreezer(id);
	}

}
