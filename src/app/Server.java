package app;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.net.ServerSocket;

public class Server {
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final ArrayList<ClientThread> threads = new ArrayList<ClientThread>();

	public static void main(String args[]) {
	
	    int portNumber = 2222;
	   
	
	    try {
	      serverSocket = new ServerSocket(portNumber);
	    } catch (IOException e) {
	      System.out.println(e);
	    }
	    System.out.println("Server started");
	    int index = 0;
	    while (true) {
	      try {
	    	System.out.println("BlaBlaBla");
	        clientSocket = serverSocket.accept();
	        System.out.println("Connection from: " + clientSocket.getInetAddress());
	        
			ClientThread thread = new ClientThread(clientSocket, threads);
			thread.start();
			threads.add(thread);
			
	
	      } catch (IOException e) {
	        System.out.println(e);
	      }
	    }
	}
}


class ClientThread extends Thread {

  private DataInputStream is = null;
  private Socket clientSocket = null;
  private final ArrayList<ClientThread> threads;
  ClientConnectionInfo cci;

  private int maxClientsCount;

  public ClientThread(Socket clientSocket, ArrayList<ClientThread> threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;

  }
 

public void run() {

    ListsSingleton listsSingleton = ListsSingleton.getInstance();
    
    try {
    	PrintStream os;
    	BufferedReader lines;
    	String login;
    	
	  is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      listsSingleton.getPrintStreamList().add(os);
      lines = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      

      for (int i = 0; i < threads.size(); i++) {
          if (threads.get(i) != null && threads.get(i) == this) {
            try {
            	os.println("Podaj login: ");
          	  	login = (lines.readLine());
          	  	cci = new ClientConnectionInfo(os, lines, login);
          	    Menu menu = new Menu(cci);
				menu.startingMenu();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
          }
        }
      
      while (true) {
		String line = lines.readLine();
        if (line.startsWith("0")) {
          break;
        }

      }

      for (int i = 0; i < threads.size(); i++) {
        if (threads.get(i) == this) {
        	threads.get(i).interrupt();
        }
      }

      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    } 
  }
}