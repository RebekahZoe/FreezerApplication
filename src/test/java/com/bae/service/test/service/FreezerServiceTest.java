package com.bae.service.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.FreezerService;
import com.bae.service.itemDoesntexistException;

@RunWith(SpringRunner.class)
public class FreezerServiceTest {

	private List<Freezers> freezers;
	private Freezers freezer;
	private Freezers freezerWithId;
	final Long id = 1L;
 	@InjectMocks
    private FreezerService service;

    @Mock
    private FreezerRepo repo;
    
    @Before
    public void setup() {
    	this.freezers = new ArrayList<>();
    	this.freezer = new Freezers("kitchen freezer");
    	this.freezers.add(freezer);
    	this.freezerWithId = new Freezers(freezer.getFreezerName()); 
    	this.freezerWithId.setId(1L);
    }
	@Test
	public void readFreezersTest() { 
        Mockito.when(repo.findAll()).thenReturn(freezers);
        assertTrue("Returned no freezers", this.service.readFreezers().size()>0);
			
	}
	
	@Test
	public void addFreezerTest() {
		
		when(this.repo.save(freezer)).thenReturn(freezerWithId);

		assertEquals(this.freezerWithId, this.service.createFreezer(freezer));

		verify(this.repo, times(1)).save(this.freezer);
		
	}
	
	@Test
	public void findFreezerByIDTest() throws FreezerDoesntexistException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.freezerWithId));

		assertEquals(this.freezerWithId, this.service.findFreezerByID(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void deleteFreezerTest() throws FreezerDoesntexistException {
		
		when(this.repo.existsById(id)).thenReturn(true, false);  
		this.service.deleteFreezer(id);
		
		verify(this.repo, times(1)).deleteById(this.id);
		verify(this.repo, times(2)).existsById(id); 
	}

}