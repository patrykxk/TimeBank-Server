package app;

import java.util.Date;

public class Service {
	String name;
	String description;
	Date termin;
	Boolean reserved = false;
	String login;
	
	public Service(String login, String name, String description, Date termin) {
		super();
		this.login = login;
		this.name = name;
		this.description = description;
		this.termin = termin;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public String getLogin(){return login;}
	
	
}

