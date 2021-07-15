package org.nagarro.application2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	@OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "employer_id", referencedColumnName = "id", nullable = false)
	private List<Ticket> tickets;

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

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
		return "Employers [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + ", tickets=" + tickets + "]";
	}
	
	

}
