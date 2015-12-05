package de.sepe.tennis.tests;

import java.rmi.*;

/**
 * TServer.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public interface TServer extends Remote {

    TPlayerData getPlayerData() throws RemoteException;
}
