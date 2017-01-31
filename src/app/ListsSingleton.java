package app;

import java.io.PrintStream;
import java.util.ArrayList;


public class ListsSingleton {
	private ArrayList <PrintStream> PrintStreamList = new ArrayList<PrintStream>();
	private ArrayList <Service> serviceList = new ArrayList<Service>();
	
	private static ListsSingleton instance;
    
    private ListsSingleton(){}
    
    public static synchronized ListsSingleton getInstance(){
        if(instance == null){
            instance = new ListsSingleton();
        }
        return instance;
    }

    public ArrayList<PrintStream> getPrintStreamList() {
		return PrintStreamList;
	}
	public void setPrintStreamList(ArrayList<PrintStream> PrintStreamList) {
		this.PrintStreamList = PrintStreamList;
	}
	public ArrayList<Service> getServiceList() {
		return serviceList;
	}
	public void setServicesList(ArrayList<Service> serviceList) {
		this.serviceList = serviceList;
	}

	
}