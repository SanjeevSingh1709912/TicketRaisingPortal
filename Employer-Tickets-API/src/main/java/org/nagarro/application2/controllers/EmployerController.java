package org.nagarro.application2.controllers;

import java.util.List;

import org.nagarro.application2.exceptions.ServiceException;
import org.nagarro.application2.constants.Constants;
import org.nagarro.application2.exceptions.ControllerException;
import org.nagarro.application2.model.Employer;
import org.nagarro.application2.services.EmployerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employer")
public class EmployerController implements Constants {

	@Autowired
	private EmployerServices employerServices;

	@GetMapping
	public List<Employer> getAllEmployer() {
		return this.employerServices.getAll();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployer(@PathVariable("id") int id) {
		try {
			Employer employer = this.employerServices.getById(id);
			return new ResponseEntity<Employer>(employer, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping("/{username}/{password}")
	public ResponseEntity<?> getEmployerByUsernameAndPassword(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		try {
			Employer employer = this.employerServices.getByUsernameAndPassword(username, password);
			return new ResponseEntity<Employer>(employer, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping("/Byusername/{username}")
	public ResponseEntity<?> getEmployerByUsername(@PathVariable("username") String username) {
		try {
			Employer employer = this.employerServices.getByUsername(username);
			return new ResponseEntity<Employer>(employer, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping
	public ResponseEntity<?> addEmployer(@RequestBody Employer employer) {
		try {
			Employer savedEmployer = this.employerServices.addOrUpdate(employer);
			return new ResponseEntity<Employer>(savedEmployer, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Employer employer, @PathVariable int id) {
		employer.setId(id);
		try {
			Employer updatedEmployer = employerServices.addOrUpdate(employer);
			return new ResponseEntity<Employer>(updatedEmployer, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		try {
			employerServices.deleteById(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getErrorCode(), e.getErrorMessage());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
