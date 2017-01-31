package app;
import java.io.IOException;
import java.util.ArrayList;



public class Menu{
	ClientConnectionInfo cci;
	ListsSingleton listsSingleton = ListsSingleton.getInstance();
	ServicesManager servicesManager;
	ArrayList<Service> serviceList = listsSingleton.getServiceList();
	
	public Menu(ClientConnectionInfo cci){
		this.cci = cci;
	}

    void startingMenu() throws IOException, ClassNotFoundException {
        servicesManager = new ServicesManager(cci);
        cci.getOs().println("------------------------------------------------------------------");
        cci.getOs().println("------------ Witaj w naszym systemie banku czasu-----------------");
        int wybor = menu();
        cci.getOs().flush();
        while(wybor!=0){
            switch(wybor) {
                case 1:
                	cci.getOs().flush();
                    servicesManager.addService(cci.getLogin());
                    break;
                case 2:
                	cci.getOs().flush();
                    servicesManager.removeService();
                    break;
                case 3:
                    servicesManager.printUserServices();
                    break;
                case 4:
                    servicesManager.bookService();
                    break;
                case 0:
                	cci.getOs().println("0");
                	
                	System.exit(0);
            }
            cci.getOs().println("\nWcisnij Enter, aby kontynuowac...");
            try {
            	cci.getLines().readLine();
            }
            catch(IOException e)
            {
            	e.printStackTrace();
            }

            wybor = menu();
        }


        }
    private int menu() {
        cci.getOs().println("--------------------"+ cci.getLogin() + " co chcesz zrobic?---------------------------");
        cci.getOs().println("-------------Wprowadz odpowiednia cyfre z zadaniem:---------------");
        cci.getOs().println("------------------------------------------------------------------");
        cci.getOs().println(" ");
        cci.getOs().println("                 [1] - Dodaj usluge");
        cci.getOs().println("                 [2] - Usun usluge");
        cci.getOs().println("                 [3] - Sprawdz swoje uslugi");       
        cci.getOs().println("                 [4] - Zarezerwuj dostepna usluge");
        cci.getOs().println("                 [0] - Wyjdz");

        String odczyt = "";
		try {
			odczyt = cci.getLines().readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		int wybor = 0 ;
		if (odczyt !=null && !"".equals(odczyt) ){
			try {
			    wybor = Integer.parseInt(odczyt);
			} catch (NumberFormatException e) {
				System.out.println("Podaj liczbê!");
				System.out.println(e.getMessage());
				menu();
			}
		}
		
        return wybor;
    }
    
    
    
    
    
}
    
 