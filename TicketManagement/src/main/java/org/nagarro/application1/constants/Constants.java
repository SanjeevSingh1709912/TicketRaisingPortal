package org.nagarro.application1.constants;

public interface Constants {

	// All html pages name
	public static final String LOGIN_PAGE = "login";
	public static final String HOME_PAGE = "home";
	public static final String SIGNUP_PAGE = "signup";
	public static final String RAISE_TICKET_PAGE = "raiseTicket2";

	// All pages URLs
	public static final String LOGIN_PAGE_URL = "/login";
	public static final String HOME_PAGE_URL = "/home";
	public static final String SIGNUP_PAGE_URL = "/signup";
	public static final String RAISE_TICKET_PAGE_URL = "/ticket";
	public static final String EDIT_TICKET_URL = "/edit";

	// All tasks URLs
	public static final String LOGIN_TASK_URL = "/loginTask";
	public static final String DELETE_TICKET_TASK_URL = "/ticketDeleteTask";
	public static final String SIGNUP_TASK_URL = "/signupTask";
	public static final String RAISE_TICKET_TASK_URL = "/raiseTicketTask";
	public static final String EDIT_TICKET_TASK_URL = "/editTicketTask";

	// All redirect URLs
	public static final String REDIRECT_TO_HOME_PAGE = "redirect:/home";
	public static final String REDIRECT_TO_LOGIN_PAGE = "redirect:/login";

	// Login and Sign up errors and variables
	public static final String LOGIN_ERROR_VARIABLE = "loginError";
	public static final String SIGNUP_ERROR_VARIABLE = "signupError";
	public static final String USERNAME_OR_PASSWORD_INCORRECT_ERROR = "*username or password incorrect";
	public static final String USERNAME_ALREADY_EXIST_ERROR = "*username already exist !!";
	public static final String FIRSTNAME_BLANK_ERROR = "*First name must not be blank !!";
	public static final String LASTNAME_BLANK_ERROR = "*Last name must not be blank !!";
	public static final String USERNAME_BLANK_ERROR = "*Username must not be blank !!";
	public static final String PASSWORD_BLANK_ERROR = "*Password must not be blank !!";
	public static final String USERNAME_SIZE_ERROR = "*Size must be between 5-12 characters !!";
	public static final String PASSWORD_SIZE_ERROR = "*Size must be between 5-12 characters !!";

	// Other variables
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String LOGGED_IN_USER_VARIABLE = "loggedInUser";
	public static final String TICKET_LIST_VARIABLE = "ticketList";
	public static final String SIGNUP_USER_VARIABLE = "signupUser";
	public static final String TICKET_NAME_VARIABLE = "ticketName";
	public static final String EDIT_TICKET_VARIABLE = "editTicket";
	public static final String EDIT_TICKET_ID_VARIABLE = "editTicketId";
	public static final String TICKET_ID_VARIABLE = "ticketId";

	// Info in Logs
	public static final String LOGIN_TASK_METHOD_CALLED = "loginTask() called";
	public static final String SIGNUP_TASK_METHOD_CALLED = "signupTask() called";
	public static final String ADD_TICKET_TASK_METHOD_CALLED = "addTicketTask() called";
	public static final String TICKET_DELETE_METHOD_CALLED = "ticketDelete() called";
	public static final String EDIT_TICKET_TASK_METHOD_CALLED = "editTicketTask() called";
	public static final String USERNAME_AND_PASSWORD_MATCHED_INFO = "username and password matched successfuly";
	public static final String GET_EMPLOYER_BY_USERNAME_AND_PASSWORD_METHOD_CALLED = "getEmployerByUsernameAndPassword() called";
	public static final String SIGNUP_USERNAME_VALIDATION_METHOD_CALLED = "signupUsernameValidation() called";
	public static final String ADD_EMPLOYER_METHOD_CALLED = "addEmployer() called";
	public static final String GET_EMPLOYER_BY_ID_METHOD_CALLED = "getEmployerById() called";
	public static final String UPDATE_EMPLOYER_METHOD_CALLED = "updateEmployer() called";
	public static final String DELETE_TICKET_METHOD_CALLED = "deleteTicket() called";
	public static final String GET_TICKET_BY_ID_METHOD_CALLED = "getTicketById() called";
	public static final String EDIT_TICKET_METHOD_CALLED = "editTicket() called";

	// All used APIs
	public static final String GET_EMPLOYER_BY_USERNAME_AND_PASSWORD_API = "http://localhost:8282/employer/";
	public static final String GET_EMPLOYER_BY_USERNAME_API = "http://localhost:8282/employer/Byusername/";
	public static final String ADD_EMPLOYER_API = "http://localhost:8282/employer";
	public static final String GET_EMPLOYER_BY_ID_API = "http://localhost:8282/employer/";
	public static final String UPDATE_EMPLOYER_BY_ID_API = "http://localhost:8282/employer/";
	public static final String DELETE_TICKET_BY_ID_API = "http://localhost:8282/ticket/";
	public static final String GET_TICKET_BY_ID_API = "http://localhost:8282/ticket/";
	public static final String EDIT_TICKET_BY_ID_API = "http://localhost:8282/ticket/";

	// Application content type
	public static final String APPLICATION_CONTENT = "application/json";

	// Log warnings
	public static final String USERNAME_OR_PASSWORD_BLANK_WARNING = "username or password blank";
	public static final String INVALID_CREDENTIALS_WARNING = "invalid credentials";
	public static final String USERNAME_ALREADY_EXIST_WARNING = "username already exists";

}
