package org.nagarro.application1.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.nagarro.application1.constants.Constants;
import org.nagarro.application1.model.Employer;
import org.nagarro.application1.utility.GlobalResources;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class ApiServices implements Constants {

	private Logger logger = GlobalResources.getLogger(ApiServices.class);
	RestTemplate restTemplate = new RestTemplate();

	// This method gets employer's details from REST API using username and password
	public Employer getEmployerByUsernameAndPassword(String username, String password)
			throws JsonParseException, JsonMappingException, IOException {
		logger.info(GET_EMPLOYER_BY_USERNAME_AND_PASSWORD_METHOD_CALLED);
		Employer emp = null;
		if (username.isBlank() || password.isBlank()) {
			logger.warn(USERNAME_OR_PASSWORD_BLANK_WARNING);
			return emp;
		}
		String url = GET_EMPLOYER_BY_USERNAME_AND_PASSWORD_API + username + "/" + password;
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		if (entity.getBody() == null) {
			logger.warn(INVALID_CREDENTIALS_WARNING);
			return emp;
		}
		if (entity.getStatusCodeValue() == 200) {
			logger.info(USERNAME_AND_PASSWORD_MATCHED_INFO);
			String body = entity.getBody();
			emp = new ObjectMapper().readValue(body, Employer.class);
			return emp;
		} else {
			logger.error(entity.getBody());
			return emp;
		}
	}

	// This method validate the pre-existing of signup username into database
	public Boolean SignupUsernameValidation(String username)
			throws JsonParseException, JsonMappingException, IOException {
		logger.info(SIGNUP_USERNAME_VALIDATION_METHOD_CALLED);
		String url = GET_EMPLOYER_BY_USERNAME_API + username;
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		if (entity.getStatusCodeValue() == 200) {
			if (entity.getBody() == null)
				return false;
			logger.warn(USERNAME_ALREADY_EXIST_WARNING);
			return true;
		} else {
			logger.error(entity.getBody());
			return true;
		}
	}

	// This method add employer's details using REST API
	public void addEmployer(Employer emp) throws JsonGenerationException, JsonMappingException, IOException {
		logger.info(ADD_EMPLOYER_METHOD_CALLED);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(emp);
		Client client = Client.create();
		WebResource webResource = client.resource(ADD_EMPLOYER_API);
		ClientResponse response = webResource.type(APPLICATION_CONTENT).post(ClientResponse.class, json);
		if (response.getStatus() != 200)
			logger.error("Error in saving employer");
		response.close();
	}

	// This method get employer's details by ID using REST API
	public Employer getEmployerById(int id) throws JsonParseException, JsonMappingException, IOException {
		logger.info(GET_EMPLOYER_BY_ID_METHOD_CALLED);
		Employer emp = null;
		String url = GET_EMPLOYER_BY_ID_API + id;
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		String body = entity.getBody();
		if (entity.getStatusCodeValue() == 200) {
			emp = new ObjectMapper().readValue(body, Employer.class);
			return emp;
		} else
			logger.error(entity.getBody());
		return emp;
	}

	// This method update the Employer's details using REST API
	public void updateEmployer(Employer emp) throws JsonGenerationException, JsonMappingException, IOException {
		logger.info(UPDATE_EMPLOYER_METHOD_CALLED);
		int id = emp.getId();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(emp);
		Client client = Client.create();
		WebResource webResource = client.resource(UPDATE_EMPLOYER_BY_ID_API + id);
		ClientResponse response = webResource.type(APPLICATION_CONTENT).put(ClientResponse.class, json);
		response.close();
	}

	// This method takes ticket ID and delete it using REST API
	public void deleteTicket(int id) {
		logger.info(DELETE_TICKET_METHOD_CALLED);
		String url = DELETE_TICKET_BY_ID_API + id;
		try {
			restTemplate.delete(url);
		} catch (RestClientException e) {
			logger.error(e.getMessage());
		}
	}

}
