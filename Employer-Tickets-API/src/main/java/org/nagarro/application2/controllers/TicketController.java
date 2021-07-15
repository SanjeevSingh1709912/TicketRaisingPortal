package org.nagarro.application2.controllers;

import java.util.List;

import org.nagarro.application2.constants.Constants;
import org.nagarro.application2.exceptions.ControllerException;
import org.nagarro.application2.exceptions.ServiceException;
import org.nagarro.application2.model.Ticket;
import org.nagarro.application2.services.TicketServices;
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
@RequestMapping("/ticket")
public class TicketController implements Constants {

	@Autowired
	private TicketServices ticketService;

	@GetMapping
	public List<Ticket> getAll() {
		return ticketService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			Ticket ticket = ticketService.getById(id);
			return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Ticket ticket) {
		try {
			Ticket savedticket = ticketService.addOrUpdate(ticket);
			return new ResponseEntity<Ticket>(savedticket, HttpStatus.OK);
		} catch (ServiceException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException(CONTROLLER_ERROR_CODE, SOMETHING_WRONG_IN_CONTROLLER);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> add(@RequestBody Ticket ticket, @PathVariable int id) {
		ticket.setId(id);
		try {
			Ticket updatedticket = ticketService.addOrUpdate(ticket);
			return new ResponseEntity<Ticket>(updatedticket, HttpStatus.OK);
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
			ticketService.deleteById(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getErrorCode(), e.getErrorMessage());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
