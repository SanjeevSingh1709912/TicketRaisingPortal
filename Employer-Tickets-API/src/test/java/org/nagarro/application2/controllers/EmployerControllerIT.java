package org.nagarro.application2.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nagarro.application2.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployerControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGetAll() throws Exception {
		@SuppressWarnings("unchecked")
		List<Employer> employers = testRestTemplate.getForObject("http://localhost:" + port + "/employer", List.class);
		Assertions.assertNotNull(employers);
	}
}
