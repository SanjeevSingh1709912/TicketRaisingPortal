package org.nagarro.application1.model;

import java.time.LocalDate;

public class Ticket {
	
	private int id;
	private String ticketName;
	private String description;
	private String date = LocalDate.now().toString();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticketName=" + ticketName + ", description=" + description + ", date=" + date
				+ ", employer=" + "]";
	}
	
	
	

}
