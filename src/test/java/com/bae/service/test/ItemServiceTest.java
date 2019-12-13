package com.bae.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;
import com.bae.service.FreezerService;
import com.bae.service.ItemService;

class ItemServiceTest {

	private List<Items> items;
	private Items item;
	private Items itemWithId;
	final Long id = 1L;
 	@InjectMocks
    private ItemService service;

    @Mock
    private ItemsRepo repo;
    
    @Before
    public void setup() {
    	this.items = new ArrayList<>();
    	this.item = new Items("curry");
    	this.items.add(item);
    	this.itemWithId = new Items(item.getItemName());
    	this.itemWithId.setId(1L);
    }
	@Test
	public void readItemsTest() { 
        Mockito.when(repo.findAll()).thenReturn(items);
        assertTrue("Returned no freezers", this.service.readItems().size()>0);
			
	}
	
	@Test
	public void addFreezerTest() {
		
		when(this.repo.save(item)).thenReturn(itemWithId);

		assertEquals(this.itemWithId, this.service.createItem(item));

		verify(this.repo, times(1)).save(this.item);
		
	}
	
