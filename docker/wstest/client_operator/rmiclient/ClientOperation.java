package rmiclient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import rmiinterface.RMIInterface;

public class ClientOperation {

	private static RMIInterface look_up;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException {
		
		String host = args[0];
		String port = args[1];
		String file = args[2];

		look_up = (RMIInterface) Naming.lookup("rmi://"+host+":"+port+"/MyServer");

		look_up.setServer(true);

		int response = look_up.executeCommand();
		
		if(response == 0){
			String s;
			Process p;
			try {
				String command = "sudo sh " + file;
				System.out.println(command);
			    p = Runtime.getRuntime().exec(command);
			    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			    while ((s = br.readLine()) != null)
					System.out.println(s);
			    p.waitFor();
			    System.out.println ("exit: " + p.exitValue());
			    p.destroy();
			} catch (Exception e) {}
		}
	}
}
