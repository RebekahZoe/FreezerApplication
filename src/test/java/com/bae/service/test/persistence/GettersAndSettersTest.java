package com.bae.service.test.persistence;

import java.util.Set;


import org.junit.Assert;
import org.junit.Test;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;

public class GettersAndSettersTest {

	@Test
	public void setFreezerNameTest() {
		Freezers freezer = new Freezers();
        freezer.setFreezerName("kitchen freezer");
		Assert.assertEquals("kitchen freezer", freezer.getFreezerName());
	}
	
	@Test
	public void getItemsTest() {
		Set<Items> items = null; 
		Set<Items> itemsActual = Freezers.getItems();
		Assert.assertEquals(items,itemsActual);
	}
	
	@Test
	public void setItemsTest() {
		
		Set<Items> items = null; 
		Freezers.setItems(items);
	}

	@Test
	public void setItemNameTest() {
		Items item = new Items();
        item.setItemName("curry");
		Assert.assertEquals("curry", item.getItemName());
	}
	
	@Test
	public void setItemQuantityTest() {
		Items item = new Items();
        item.setQuantity(3);
		Assert.assertEquals(3, item.getQuantity());
	}
	
	@Test
	public void getFreezersTest() {
		Set<Freezers> freezers = null; 
		Freezers freezersActual = Items.getFreezers();
		Assert.assertEquals(freezers,freezersActual);
	}
	
	@Test
	public void setFreezersTest() {
		Freezers freezer = new Freezers("kitchen freezer");
		Items.setFreezers(freezer);
	}
}
