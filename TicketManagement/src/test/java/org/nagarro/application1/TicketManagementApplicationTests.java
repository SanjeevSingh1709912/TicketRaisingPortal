package org.nagarro.application1;

import org.junit.jupiter.api.Test;
import org.nagarro.application1.controllers.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TicketManagementApplicationTests {

	@Autowired
	MainController mainController;
	
	@Test
	void contextLoads() {
		Assert.notNull(mainController, "object is not null");
	}

}
