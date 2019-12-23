package com.bae.service.test.unit.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Items;
import com.bae.rest.ItemsController;
import com.bae.service.ItemService;
import com.bae.service.ItemDoesntexistException;

@RunWith(SpringRunner.class)
public class ItemControllerTest {

	@InjectMocks
	private ItemsController controller;

	@Mock
	private ItemService service;

	private List<Items> itemList;

	private Items testItem;

	private Items testItemWithID;

	final long id = 1L;
	
	@Before
	public void init() {
		this.itemList = new ArrayList<>();
		this.itemList.add(testItem);
		this.testItem = new Items("curry",3);
		this.testItemWithID = new Items(testItem.getItemName(),testItem.getQuantity());
		this.testItemWithID.setId(id);
	}

	@Test
	public void createItemTest() {
		when(this.service.createItem(testItem)).thenReturn(testItemWithID);

		assertEquals(this.testItemWithID, this.controller.createItems(testItem));

		verify(this.service, times(1)).createItem(this.testItem);
	}

	@Test
	public void deleteItemTest() throws ItemDoesntexistException {
		this.controller.deleteItem(id);

		verify(this.service, times(1)).deleteItem(id);
	}

	@Test
	public void findItemByIDTest() throws ItemDoesntexistException {
		when(this.service.findItemByID(this.id)).thenReturn(this.testItemWithID);

		assertEquals(this.testItemWithID, this.controller.getItem(this.id));

		verify(this.service, times(1)).findItemByID(this.id);
	}

	@Test
	public void getAllItemsTest() {

		when(service.readItems()).thenReturn(this.itemList);

		assertFalse("Controller has found no Items", this.controller.getAllItems().isEmpty());

		verify(service, times(1)).readItems();
	}
	
	@Test
	public void updateItemTest() throws ItemDoesntexistException {
		Items newItem = new Items("curry",2);
		Items updatedItem = new Items(newItem.getItemName(), newItem.getQuantity());
		updatedItem.setId(this.id);

		when(this.service.updateItem(newItem, this.id)).thenReturn(updatedItem);

		assertEquals(updatedItem, this.controller.updateItem(this.id, newItem));

		verify(this.service, times(1)).updateItem(newItem, this.id);
	}


}