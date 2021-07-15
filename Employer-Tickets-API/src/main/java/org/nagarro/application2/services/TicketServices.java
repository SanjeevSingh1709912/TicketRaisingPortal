package org.nagarro.application2.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.nagarro.application2.constants.Constants;
import org.nagarro.application2.dao.TicketRepository;
import org.nagarro.application2.exceptions.ServiceException;
import org.nagarro.application2.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServices implements Constants {

	@Autowired
	private TicketRepository ticketRepository;

	public Ticket addOrUpdate(Ticket ticket) {
		if (ticket.getTicketName().isEmpty())
			throw new ServiceException(TICKET_BLANK_ERROR_CODE, TICKET_BLANK_ERROR);
		try {
			Ticket savedticket = this.ticketRepository.save(ticket);
			return savedticket;
		} catch (IllegalArgumentException e) {
			throw new ServiceException(TICKET_BLANK_ERROR_CODE, TICKET_NULL_ERROR);
		} catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}

	public void deleteById(int id) {
		try {
			this.ticketRepository.deleteById(id);
		} catch (NoSuchElementException e) {
			throw new ServiceException(ID_NOT_EXIST_ERROR_CODE, ID_NOT_EXIST);
		} catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}

	public List<Ticket> getAll() {
		try {
			return this.ticketRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}

	public Ticket getById(int id) {
		try {
			return this.ticketRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceException(ID_NOT_EXIST_ERROR_CODE, ID_NOT_EXIST);
		} catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}

}
