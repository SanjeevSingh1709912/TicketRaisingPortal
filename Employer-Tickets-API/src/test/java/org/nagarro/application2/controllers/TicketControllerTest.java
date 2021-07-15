package org.nagarro.application2.controllers;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.nagarro.application2.model.Ticket;
import org.nagarro.application2.services.EmployerServices;
import org.nagarro.application2.services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class TicketControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TicketServices ticketServices;
	
	@MockBean
	private EmployerServices employerServices;

	@Test
	void testGetAll() throws Exception {
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		t1.setId(101);
		t1.setTicketName("Ticket Name 1");
		t1.setDescription("Ticket 1 for test");
		t1.setDate("10-12-2013");
		
		t2.setId(102);
		t2.setTicketName("Ticket Name 2");
		t2.setDescription("Ticket 2 for test");
		t2.setDate("10-12-2013");
		
		Mockito.when(ticketServices.getAll()).thenReturn(Arrays.asList(t1, t2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ticket")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());			
	}
	

	@Test
	void testGetById() throws Exception {
		
		Ticket t1 = new Ticket();
		t1.setId(101);
		t1.setTicketName("Ticket Name 1");
		t1.setDescription("Ticket 1 for test");
		t1.setDate("10-12-2013");
		
		Mockito.when(ticketServices.getById(101)).thenReturn(t1);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ticket/{id}", 101)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());			
	}

}
