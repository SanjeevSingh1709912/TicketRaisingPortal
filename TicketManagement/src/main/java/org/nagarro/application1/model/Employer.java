package org.nagarro.application1.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.nagarro.application1.constants.Constants;

public class Employer implements Constants{
	
	private int id;
		
	@NotBlank(message = FIRSTNAME_BLANK_ERROR)
	private String firstname;
	
	@NotBlank(message = LASTNAME_BLANK_ERROR)
	private String lastname;
	
	@NotBlank(message = USERNAME_BLANK_ERROR)
	@Size(min=5,max=12,message = USERNAME_SIZE_ERROR)
	private String username;
	
	@NotBlank(message = PASSWORD_BLANK_ERROR)
	@Size(min=5,max=12,message = PASSWORD_SIZE_ERROR)
	private String password;
	
	private List<Ticket> tickets;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	@Override
	public String toString() {
		return "Employer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", tickets=" + tickets + "]";
	}
}
