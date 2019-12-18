package com.bae.service.test.persistence;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;

public class GettersAndSettersTest {

	private Freezers freezer = new Freezers();

	@Before
	public void setup() {
		this.freezer = new Freezers();
	}

	@Test
	public void setFreezerNameTest() {
		freezer.setFreezerName("kitchen freezer");
		Assert.assertEquals("kitchen freezer", freezer.getFreezerName());
	}

	@Test
	public void getItemsTest() {
		Set<Items> items =  new HashSet<>();
		Set<Items> itemsActual = freezer.getItems();
		Assert.assertEquals(items, itemsActual);
	}

	@Test
	public void setItemsTest() {
		Set<Items> items = new HashSet<>();
		freezer.setItems(items);
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

}
