package org.nagarro.application2.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nagarro.application2.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGetAll() throws Exception {
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = testRestTemplate.getForObject("http://localhost:" + port + "/ticket", List.class);
		Assertions.assertNotNull(tickets);
	}
	
	
	
}
