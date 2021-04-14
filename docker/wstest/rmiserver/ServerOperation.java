package rmiserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.*;

import rmiinterface.RMIInterface;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface{

    private static final long serialVersionUID = 1L;
	private boolean server;
	private boolean client;

	public boolean isServer() throws RemoteException {
		return server;
	}

	public boolean isClient() throws RemoteException {
		return client;
	}

	public void setServer(boolean server) throws RemoteException {
		this.server = server;
	}

	public void setClient(boolean client) throws RemoteException {
		this.client = client;
	}

    protected ServerOperation() throws RemoteException {
        super();
    }

    @Override
    public int executeCommand() throws RemoteException{
		while(true){
			if(isServer() && isClient()){
				System.out.println("Connected");
				return 0;	
			}
			else{
				System.out.println("Not connected " + isServer() + " " + isClient());
			}
		}
	}

    public static void main(String[] args){
		String host = args[0];
		String port = args[1];
        try {
        	System.setProperty("java.rmi.server.hostname",host);
            Naming.rebind("rmi://"+host+":"+port+"/MyServer", new ServerOperation());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
