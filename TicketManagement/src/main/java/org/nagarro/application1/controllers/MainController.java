package org.nagarro.application1.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.nagarro.application1.constants.Constants;
import org.nagarro.application1.model.Employer;
import org.nagarro.application1.model.Ticket;
import org.nagarro.application1.service.ApiServices;
import org.nagarro.application1.utility.GlobalResources;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController implements Constants {

	@Autowired
	ApiServices apiService;

	private Logger logger = GlobalResources.getLogger(MainController.class);
	public static Employer loggedInEmployer = null;
	public static int editTicketId = 0;

	// Mapping of html pages with unique URLs

	@RequestMapping(LOGIN_PAGE_URL)
	public String login(Model model) {
		loggedInEmployer = null;
		model.addAttribute(LOGIN_ERROR_VARIABLE, "");
		return LOGIN_PAGE;
	}

	@RequestMapping(HOME_PAGE_URL)
	public String home(Model model) {
		if (loggedInEmployer == null)
			return REDIRECT_TO_LOGIN_PAGE;
		editTicketId = 0;
		List<Ticket> ticketList = loggedInEmployer.getTickets();
		model.addAttribute(TICKET_LIST_VARIABLE, ticketList);
		model.addAttribute(LOGGED_IN_USER_VARIABLE, loggedInEmployer);
		return HOME_PAGE;
	}

	@RequestMapping(SIGNUP_PAGE_URL)
	public String signup(Model model) {
		loggedInEmployer = null;
		model.addAttribute(SIGNUP_ERROR_VARIABLE, "");
		model.addAttribute(SIGNUP_USER_VARIABLE, new Employer());
		return SIGNUP_PAGE;
	}

	@RequestMapping(RAISE_TICKET_PAGE_URL)
	public String addTicket(Model model,
			@RequestParam(name = TICKET_NAME_VARIABLE, defaultValue = "") String ticketName) {
		if (loggedInEmployer == null)
			return REDIRECT_TO_LOGIN_PAGE;
		model.addAttribute(TICKET_NAME_VARIABLE, ticketName);
		model.addAttribute(LOGGED_IN_USER_VARIABLE, loggedInEmployer);
		model.addAttribute(EDIT_TICKET_VARIABLE, new Ticket());
		model.addAttribute(EDIT_TICKET_ID_VARIABLE, editTicketId);
		return RAISE_TICKET_PAGE;
	}

	@RequestMapping(EDIT_TICKET_URL)
	public String editTicket(Model model, @RequestParam(TICKET_ID_VARIABLE) int id)
			throws JsonParseException, JsonMappingException, IOException {
		editTicketId = id;
		Ticket ticket = loggedInEmployer.getTickets().stream().filter(i -> i.getId() == editTicketId).findAny()
				.orElse(null);
		model.addAttribute(EDIT_TICKET_VARIABLE, ticket);
		model.addAttribute(EDIT_TICKET_ID_VARIABLE, editTicketId);
		model.addAttribute(LOGGED_IN_USER_VARIABLE, loggedInEmployer);
		return RAISE_TICKET_PAGE;
	}

	// Required methods to perform tasks and their mapping with unique URLs

	// Login task -> It validate user credentials then redirect to home page if credentials matched.
	@PostMapping(LOGIN_TASK_URL)
	public String loginTask(@RequestParam(USERNAME) String username, @RequestParam(PASSWORD) String password,
			Model model) throws JsonParseException, JsonMappingException, IOException {
		logger.info(LOGIN_TASK_METHOD_CALLED);
		Employer emp = apiService.getEmployerByUsernameAndPassword(username, password);
		if (emp == null) {
			model.addAttribute(LOGIN_ERROR_VARIABLE, USERNAME_OR_PASSWORD_INCORRECT_ERROR);
			return LOGIN_PAGE;
		}
		loggedInEmployer = emp;
		return REDIRECT_TO_HOME_PAGE;
	}

	// Sign Up Task -> It validates all input fields and pre-existing of username then redirect to home page.
	@PostMapping(SIGNUP_TASK_URL)
	public String signupTask(@Valid @ModelAttribute(SIGNUP_USER_VARIABLE) Employer emp, BindingResult result,
			Model model) throws JsonGenerationException, JsonMappingException, IOException {
		logger.info(SIGNUP_TASK_METHOD_CALLED);
		if (result.hasErrors())
			return SIGNUP_PAGE;

		if (apiService.SignupUsernameValidation(emp.getUsername())) {
			model.addAttribute(SIGNUP_ERROR_VARIABLE, USERNAME_ALREADY_EXIST_ERROR);
			return SIGNUP_PAGE;
		}
		apiService.addEmployer(emp);
		loggedInEmployer = apiService.getEmployerByUsernameAndPassword(emp.getUsername(), emp.getPassword());
		return REDIRECT_TO_HOME_PAGE;
	}

	// Raise Ticket Task -> It takes input ticket and update it in logged in employer's data.
	@PostMapping(RAISE_TICKET_TASK_URL)
	public String addTicketTask(@ModelAttribute Ticket ticket)
			throws JsonGenerationException, JsonMappingException, IOException {
		logger.info(ADD_TICKET_TASK_METHOD_CALLED);
		loggedInEmployer.getTickets().add(ticket);
		apiService.updateEmployer(loggedInEmployer);
		loggedInEmployer = apiService.getEmployerById(loggedInEmployer.getId());
		return REDIRECT_TO_HOME_PAGE;
	}

	// Delete Ticket Task -> It takes id of ticket and delete that ticket from logged in employer's data
	@GetMapping(DELETE_TICKET_TASK_URL)
	public String ticketDelete(@RequestParam("id") int id)
			throws JsonParseException, JsonMappingException, IOException {
		logger.info(TICKET_DELETE_METHOD_CALLED);
		apiService.deleteTicket(id);
		loggedInEmployer.getTickets().removeIf(t -> t.getId() == id);
		apiService.updateEmployer(loggedInEmployer);
		loggedInEmployer = apiService.getEmployerById(loggedInEmployer.getId());
		return REDIRECT_TO_HOME_PAGE;
	}

	// Edit Ticket Task -> It takes id of ticket and update the edited ticket.
	@PostMapping(EDIT_TICKET_TASK_URL)
	public String editTicketTask(@ModelAttribute Ticket ticket)
			throws JsonParseException, JsonMappingException, IOException {
		logger.info(EDIT_TICKET_TASK_METHOD_CALLED);
		loggedInEmployer.getTickets().removeIf(t -> t.getId() == editTicketId);
		loggedInEmployer.getTickets().add(ticket);
		apiService.updateEmployer(loggedInEmployer);
		editTicketId = 0;
		loggedInEmployer = apiService.getEmployerById(loggedInEmployer.getId());
		return REDIRECT_TO_HOME_PAGE;
	}
}
