package de.sepe.tennis.remote.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import de.sepe.tennis.remote.Server;
import de.sepe.tennis.remote.data.BallData;
import de.sepe.tennis.remote.data.PlayerData;

/**
 * Server Implementation.
 * 
 * @author Sebastian Peters
 * @since 15.12.2003
 */
@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements Server {

    /** all sessions on our server */
    private Map<String, Session> sessions;

    /**
     * Constructor.
     * 
     * @throws RemoteException if failed to export object
     */
    public ServerImpl() throws RemoteException {
        super();
        sessions = new HashMap<String, Session>();
    }

    public boolean createSession(String name) throws RemoteException {
        if (sessions.get(name) != null) {
            return false;
        }
        sessions.put(name, new Session());
        System.out.println("new session started: " + name);
        return true;
    }

    public boolean removeSession(String name) throws RemoteException {
        return sessions.remove(name) != null ? true : false;
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }

    public void clearSessions() throws RemoteException {
        sessions.clear();
    }

    public BallData getBallForSession(String name) throws RemoteException {
        return sessions.get(name).getBall();
    }

    public PlayerData getInitiatorForSession(String name) throws RemoteException {
        return sessions.get(name).getInitiator();
    }

    public PlayerData getContrahentForSession(String name) throws RemoteException {
        return sessions.get(name).getContrahent();
    }

    public void setBallForSession(String name, BallData data) throws RemoteException {
        sessions.get(name).setBall(data);
    }

    public void setInitiatorForSession(String name, PlayerData data) throws RemoteException {
        sessions.get(name).setInitiator(data);

    }

    public void setContrahentForSession(String name, PlayerData data) throws RemoteException {
        sessions.get(name).setContrahent(data);

    }

    public static void main(String[] args) throws Exception {
        ServerImpl serv = new ServerImpl();
        Naming.rebind("tennisServer", serv);
        System.out.println("tennisServer bound in registry");
    }

    public boolean startSession(String name) throws RemoteException {
        return sessions.get(name).startSession();
    }
}
