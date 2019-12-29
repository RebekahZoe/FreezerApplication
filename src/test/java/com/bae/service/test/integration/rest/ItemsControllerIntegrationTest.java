package com.bae.service.test.integration.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemsControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ItemsRepo repo;

	private ObjectMapper mapper = new ObjectMapper();

	private long id;

	private Items testItem;

	private Items testItemWithID;

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testItem = new Items("curry", 2);
		this.testItemWithID = this.repo.save(this.testItem);
		this.id = this.testItemWithID.getId();
	}

	@Test
	public void testCreateItem() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/createItems").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testItem)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testItemWithID), result);
	}

	@Test
	public void testDeleteItem() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/deleteItem/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testReadItem() throws Exception {
		List<Items> itemList = new ArrayList<>();
		itemList.add(this.testItemWithID);

		String content = this.mock.perform(request(HttpMethod.GET, "/getAllItems").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(itemList), content);
	}

	@Test
	public void testFindItemByID() throws Exception {
		this.mock.perform(request(HttpMethod.GET, "/getItem/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateItem() throws Exception {
		Items newItem = new Items("curry", 2);
		Items updatedItem = new Items(newItem.getItemName(), newItem.getQuantity());
		updatedItem.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/updateItem/" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newItem)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(updatedItem), result);
	}

}
