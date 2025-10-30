package de.sepe.tennis.remote.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
    private final Map<String, Session> sessions;

    /**
     * Constructor.
     *
     * @throws RemoteException if failed to export object
     */
    public ServerImpl() throws RemoteException {
        super();
        sessions = new HashMap<>();
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
        Session session = sessions.get(name);
        if (session != null) {
            session.setSessionInactive();
            sessions.remove(name);
            return true;
        }
        return false;
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }

    public void clearSessions() throws RemoteException {
        for (Session session : sessions.values()) {
            session.setSessionInactive();
        }
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
        // Create an RMI registry in the same JVM on port 1099
        // This ensures the registry has access to all classes on the classpath
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created on port 1099");
        } catch (RemoteException e) {
            // Registry already running
            System.out.println("RMI registry already running on port 1099");
        }

        // Create and bind the server
        final ServerImpl serv = new ServerImpl();
        Naming.rebind("tennisServer", serv);
        System.out.println("tennisServer bound in registry");
        System.out.println("Server ready and waiting for connections...");
        System.out.println("Press Ctrl+C to stop.");

        // Keep the server running
        Thread.currentThread().join();
    }

    public boolean startSession(String name) throws RemoteException {
        return sessions.get(name).startSession();
    }
}
