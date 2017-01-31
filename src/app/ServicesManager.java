package app;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ServicesManager{
	ClientConnectionInfo cci;
	ListsSingleton listsSingleton = ListsSingleton.getInstance();
	private ArrayList<Service> serviceList = listsSingleton.getServiceList();
	
	public  ServicesManager(ClientConnectionInfo cci){
		this.cci = cci;
    }
    

    void addService(String login) throws IOException{
        boolean flag;
        
        String name;
    	String description;
    	Date termin = null;	
    	
    	cci.getOs().println("Podaj nazwe Twojej uslugi: ");
    	name = cci.getLines().readLine();
    	
    	cci.getOs().println("Podaj krotki opis: ");
    	description = cci.getLines().readLine();
    	
    	
    	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        do {
        	cci.getOs().println("Podaj date: (dd.MM.yyyy)");
            flag = false;
            try{
            	termin = format.parse(cci.getLines().readLine());
            }catch (ParseException ex) {
            	cci.getOs().println("Zly format daty");
            	flag = true;
            }
        }while(flag);

       
		Service service = new Service(login, name, description, termin);

		
    	cci.getOs().println("--------------------------------------------------------");
    	cci.getOs().println( "	" + name);
    	cci.getOs().println("	"  + description);
    	cci.getOs().println("	"  + termin);
    	cci.getOs().println("--------------------------------------------------------");
    	
    	if(accept()){
    		listsSingleton.getServiceList().add(service);
    		cci.getOs().println("Dodales usluge");
    		sendMessageToEveryone(login + " dodal nowa usluge");
    	}
	}
    
    void removeService(){
    	if(!printUserServices()){return;}
    	int wybor = getChoiceFromUser();
    	if(wybor==0){return;}

		if(accept()){
			if(serviceList.get(wybor-1).getReserved()==true){
				cci.getOs().println("Nie mozna usunac zarezerwowanej uslugi");
				return;
			}
			serviceList.remove(wybor-1);
			cci.getOs().println("Usunieto usluge nr"+wybor);
		}
    }

    void bookService(){
    	if(!printServices()){return;}
    	int wybor = getChoiceFromUser();
    	if(wybor==0){return;}
    	
		if(accept()){
			serviceList.get(wybor-1).setReserved(true);
			cci.getOs().println("Zarezerwowano usluge nr"+wybor);
			sendMessageToEveryone("Zarezerwowano usluge uzytkownika " + serviceList.get(wybor-1).getLogin()
					              + " o numerze "+ wybor);
		}
		
    	
    }
    private int getChoiceFromUser(){
    	cci.getOs().println("Wprowadz odpowiednia cyfre");
    	cci.getOs().println("[0] aby wyjsc");
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
				getChoiceFromUser();
			}
		}
		if(wybor==0){return 0;}
		if(wybor-1>serviceList.size()){
			cci.getOs().println("Brak uslugi o takim numerze");
			getChoiceFromUser();
		}
		return wybor;
    }

    public boolean accept(){
    	cci.getOs().println("Czy na pewno chcesz wykonac te operacje?");
    	cci.getOs().println("Wprowadz t lub n");
        String scan = null;
		try {
			scan = cci.getLines().readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return (scan.equals("t") || scan.equals("T"));
    }
    
    
    private void sendMessageToEveryone(String message){
    	for(PrintStream os : listsSingleton.getPrintStreamList()){
    		if(!os.equals(cci.getOs()))
            	os.println(message);
        }
    }
	public boolean printServices(){
		if(serviceList.isEmpty()){
			cci.getOs().println("Lista jest pusta");
			return false;
		}
			for(Service service : serviceList){
	    		if((service.getLogin()!=cci.getLogin()) && (!service.getReserved())  ){
			    	cci.getOs().println("--------------------------------------------------------");
			    	cci.getOs().println("	"  + service.getName());
			    	cci.getOs().println(serviceList.indexOf(service)+1 +  "	"  + service.getDescription());
			    	cci.getOs().println("	"  + service.getTermin());
			    	cci.getOs().println("--------------------------------------------------------");
			}
		}
			return true;
	}
	public boolean printUserServices(){
		if(serviceList.isEmpty()){
			cci.getOs().println("Lista jest pusta");
			return false;
		}
		for(Service service : serviceList){
    		if((service.getLogin()==cci.getLogin())){
		    	cci.getOs().println("--------------------------------------------------------");
		    	cci.getOs().println("	"  + service.getName());
		    	cci.getOs().println(serviceList.indexOf(service)+1 +  "	"  + service.getDescription());
		    	cci.getOs().println("	"  + service.getTermin());
		    	if(service.getReserved())
		    		cci.getOs().println("	Usluga zarezerwowana");
		    	cci.getOs().println("--------------------------------------------------------");
    		}
    	
		}
		return true;
	}
}   
    