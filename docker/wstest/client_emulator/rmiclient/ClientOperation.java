package rmiclient;

import java.net.MalformedURLException;
import java.rmi.*;
import java.net.SocketPermission;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import rmiinterface.RMIInterface;

public class ClientOperation {

	private static RMIInterface look_up;

	public static void main(String[] args){
		
		String host = args[0];
		String port = args[1];
		String hostService = args[2];
		String portService = args[3];
		String numTest = args[4];
		String timeTest = args[5];
		
		//System.getProperties().put("java.security.policy","all.policy");
		//System.setSecurityManager(new SecurityManager());
		//SocketPermission ps = new SocketPermission(host + ":" + port,"connect,resolve");
		
		//System.setProperty("java.rmi.server.hostname",host);
		
		
		try{		
			look_up = (RMIInterface) Naming.lookup("rmi://" + host + ":" + port + "/MyServer");

			look_up.setClient(true);
			
			int response = look_up.executeCommand();
			
			if(response == 0){
				String s;
				Process p;
				try {
					Thread.sleep(60000);
					String command = "java -jar \"Client.jar\" " + hostService + " " + portService + " " + numTest + " " + timeTest;
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
		} catch(MalformedURLException e1){
			
			System.out.println("Erro1");
		} catch(RemoteException e2){
			e2.printStackTrace();
		} catch(NotBoundException e3){
			System.out.println("Erro3");
		}
		
		
	}
}
