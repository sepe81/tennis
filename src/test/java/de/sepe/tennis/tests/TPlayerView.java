package de.sepe.tennis.tests;

import java.rmi.*;

/**
 * 
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public class TPlayerView {

    String name;

    int x, y;

    TServer server;

    /**
     * Constructor.
     * 
     * @throws Exception if connection to server failed
     */
    public TPlayerView() throws Exception {
        name = "Maxi";
        server = (TServer) Naming.lookup("//localhost/pData");
    }

    /**
     * Animate.
     */
    public void animate() {
        try {
            TPlayerData data = server.getPlayerData();
            x = data.x;
            y = data.y;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }
}
