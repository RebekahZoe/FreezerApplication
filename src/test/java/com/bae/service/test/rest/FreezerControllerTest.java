package com.bae.service.test.rest;

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

import com.bae.persistence.domain.Freezers;
import com.bae.rest.FreezerController;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.FreezerService;

@RunWith(SpringRunner.class)
public class FreezerControllerTest {

	@InjectMocks
	private FreezerController controller;

	@Mock
	private FreezerService service;

	private List<Freezers> freezerList;

	private Freezers testFreezer;

	private Freezers testFreezerWithID;

	final long id = 1L;
	
	@Before
	public void init() {
		this.freezerList = new ArrayList<>();
		this.freezerList.add(testFreezer);
		this.testFreezer = new Freezers("Kitchen Freezer");
		this.testFreezerWithID = new Freezers(testFreezer.getFreezerName());
		this.testFreezerWithID.setId(id);
	}

	@Test
	public void createFreezerTest() {
		when(this.service.createFreezer(testFreezer)).thenReturn(testFreezerWithID);

		assertEquals(this.testFreezerWithID, this.controller.createDuck(testFreezer));

		verify(this.service, times(1)).createFreezer(this.testFreezer);
	}

	@Test
	public void deleteFreezerTest() throws FreezerDoesntexistException {
		this.controller.deleteFreezer(id);

		verify(this.service, times(1)).deleteFreezer(id);
	}

	@Test
	public void findFreezerByIDTest() throws FreezerDoesntexistException {
		when(this.service.findFreezerByID(this.id)).thenReturn(this.testFreezerWithID);

		assertEquals(this.testFreezerWithID, this.controller.getFreezer(this.id));

		verify(this.service, times(1)).findFreezerByID(this.id);
	}

	@Test
	public void getAllFreezersTest() {

		when(service.readFreezers()).thenReturn(this.freezerList);

		assertFalse("Controller has found no Freezers", this.controller.getAllFreezers().isEmpty());

		verify(service, times(1)).readFreezers();
	}


}
