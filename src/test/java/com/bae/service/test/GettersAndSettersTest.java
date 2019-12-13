package com.bae.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
		Items item = new Items("Curry",3);
		Set<Items> items = null; 
		items.add(item);
		
	}

}
