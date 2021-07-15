package org.nagarro.application2.controllers;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.nagarro.application2.model.Employer;
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
class EmployerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployerServices employerServices;
	
	@MockBean
	private TicketServices ticketServices;
	
	
	@Test
	void testGetAllEmployer() throws Exception {
		
		Employer e1 = new Employer();
		Employer e2 = new Employer();
		
		e1.setId(101);
		e1.setFirstname("FirstNameTest 1");
		e1.setLastname("LastNameTest 1");
		e1.setUsername("Username 1");
		e1.setPassword("Password 1");
		
		e2.setId(102);
		e2.setFirstname("FirstNameTest 2");
		e2.setLastname("LastNameTest 2");
		e2.setUsername("Username 2");
		e2.setPassword("Password 2");
		
		Mockito.when(employerServices.getAll()).thenReturn(Arrays.asList(e1, e2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employer")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetEmployer() throws Exception {
		Employer e1 = new Employer();

		e1.setId(101);
		e1.setFirstname("FirstNameTest 1");
		e1.setLastname("LastNameTest 1");
		e1.setUsername("Username 1");
		e1.setPassword("Password 1");
		
		Mockito.when(employerServices.getById(101)).thenReturn(e1);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employer/{id}", 101)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());	
	}

	@Test
	void testGetEmployerByUsernameAndPassword() throws Exception {
		Employer e1 = new Employer();

		e1.setId(101);
		e1.setFirstname("FirstNameTest 1");
		e1.setLastname("LastNameTest 1");
		e1.setUsername("Username 1");
		e1.setPassword("Password 1");
		
		Mockito.when(employerServices.getById(101)).thenReturn(e1);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/employer/{username}/{password}", "Username 1","Password 1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetEmployerByUsername() throws Exception {
		Employer e1 = new Employer();

		e1.setId(101);
		e1.setFirstname("FirstNameTest 1");
		e1.setLastname("LastNameTest 1");
		e1.setUsername("Username 1");
		e1.setPassword("Password 1");
		
		Mockito.when(employerServices.getById(101)).thenReturn(e1);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/employer/Byusername/{username}", "Username 1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
