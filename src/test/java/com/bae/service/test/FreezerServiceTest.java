package com.bae.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.service.FreezerService;

import java.util.List;

@RunWith(SpringRunner.class)
public class FreezerServiceTest {

	private List<Freezers> freezers = new ArrayList<>();
 	@InjectMocks
    private FreezerService service;

    @Mock
    private FreezerRepo repo;
    
    @Before
    public void setup() {
    	freezers.add(new Freezers("kitchen freezer"));
    }
	@Test
	public void readFreezersTest() { 
        Mockito.when(repo.findAll()).thenReturn(freezers);
        assertTrue("Returned no freezers", this.service.readFreezers().size()>0);
			
	}

}
