package rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    public int executeCommand() throws RemoteException;
	public boolean isServer() throws RemoteException;
	public boolean isClient() throws RemoteException;
	public void setServer(boolean server) throws RemoteException;
	public void setClient(boolean client) throws RemoteException;
}
