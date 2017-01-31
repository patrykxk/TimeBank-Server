package app;

import java.io.BufferedReader;
import java.io.PrintStream;

public class ClientConnectionInfo {
	public PrintStream getOs() {
		return os;
	}
	public void setOs(PrintStream os) {
		this.os = os;
	}
	public BufferedReader getLines() {
		return lines;
	}
	public void setLines(BufferedReader lines) {
		this.lines = lines;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public ClientConnectionInfo(PrintStream os, BufferedReader lines, String login) {
		super();
		this.os = os;
		this.lines = lines;
		this.login = login;
	}
	private PrintStream os;
	private BufferedReader lines;
	private String login;
	
	
}
