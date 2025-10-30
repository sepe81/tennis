package de.sepe.tennis.tests;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * TServerImpl
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public class TServerImpl extends UnicastRemoteObject implements TServer {

    TPlayer player;

    /**
     * Constructor.
     * 
     * @throws RemoteException
     */
    public TServerImpl() throws RemoteException {
        player = new TPlayer();
    }

    /**
     * {@inheritDoc}
     */
    public TPlayerData getPlayerData() throws RemoteException {
        return player.getData();
    }

    public static void main(String[] args) throws Exception {
        TServerImpl serv = new TServerImpl();
        Naming.rebind("pData", serv);
        System.out.println("pData bound in registry");
    }
}
