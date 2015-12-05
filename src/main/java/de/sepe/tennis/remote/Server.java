package de.sepe.tennis.remote;

import java.rmi.*;

import de.sepe.tennis.remote.data.*;

/**
 * Server for Tennis games.
 * 
 * @author Sebastian Peters
 * @since 15.12.2003
 */
public interface Server extends Remote {
    boolean createSession(String name) throws RemoteException;

    boolean removeSession(String name) throws RemoteException;

    void clearSessions() throws RemoteException;

    boolean startSession(String name) throws RemoteException;

    BallData getBallForSession(String name) throws RemoteException;

    PlayerData getInitiatorForSession(String name) throws RemoteException;

    PlayerData getContrahentForSession(String name) throws RemoteException;

    void setBallForSession(String name, BallData data) throws RemoteException;

    void setInitiatorForSession(String name, PlayerData data) throws RemoteException;

    void setContrahentForSession(String name, PlayerData data) throws RemoteException;
}
