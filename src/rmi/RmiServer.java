package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6576223930486368371L;
	public static final String MESSAGE = "Hello world";
	
	public RmiServer() throws RemoteException {
		super(0);
	}

	public String getMessage() throws RemoteException {
		return MESSAGE;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("RMI server started");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
			
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}
		
		RmiServer obj = new RmiServer();
		Naming.rebind("//localhost/RmiServer", obj);
		System.out.println("PeerServer bound in registry");
	}
}
