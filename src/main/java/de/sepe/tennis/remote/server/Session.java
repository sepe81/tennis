package de.sepe.tennis.remote.server;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.sepe.tennis.remote.data.BallData;
import de.sepe.tennis.remote.data.PlayerData;

/**
 * Game session.
 * 
 * @author Sebastian Peters
 * @since 15.12.2003
 */
public class Session implements Runnable {

    /** session is active and once set to inactive */
    private boolean sessionActive = true;

    /** session thread */
    private Thread sessionThread;

    /** the initial player */
    private final Player initiator;

    /** the joining player */
    private final Player contrahent;

    /** the ball */
    private final Ball ball;

    /**
     * Constructor.
     */
    public Session() {
        this.initiator = new Player("Frank");
        this.initiator.setPosition(new Point2D.Double(100, 100));

        this.contrahent = new Player("Boris");
        this.contrahent.setPosition(new Point2D.Double(300, 100));

        this.ball = new Ball();
        this.ball.setDiameter(new Rectangle2D.Double(0, 0, 400, 200));
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer(sessionActive ? "active" : "inactive");
        ret.append(" session initiated by ").append(initiator.getName());
        if (contrahent == null) {
            ret.append(" (still waiting for partner to join)");
        } else {
            ret.append(" (partner is ").append(contrahent.getName()).append(")");
        }
        return ret.toString();
    }

    /**
     * Stop the session gracefully.
     */
    public void setSessionInactive() {
        sessionActive = false;
        if (sessionThread != null && sessionThread.isAlive()) {
            sessionThread.interrupt();
            sessionThread = null;
        }
    }

    /**
     * @return
     */
    public BallData getBall() {
        return ball.getData();
    }

    public void setBall(BallData data) {
        ball.setData(data);
    }

    /**
     * @return
     */
    public PlayerData getInitiator() {
        return initiator.getData();
    }

    public void setInitiator(PlayerData data) {
        initiator.setData(data);
    }

    /**
     * @return
     */
    public PlayerData getContrahent() {
        return contrahent.getData();
    }

    public void setContrahent(PlayerData data) {
        contrahent.setData(data);
    }

    /**
     * @return
     */
    public boolean isSessionActive() {
        return sessionActive;
    }

    /**
     * Move the ball & check for hits or out of bounds.
     */
    private void animateBall() {
        int status = ball.move();
        switch (status) {
        case 1:
            contrahent.incScore();
            break;
        case 2:
            initiator.incScore();
            break;
        case 0:
            ball.hit(initiator);
            ball.hit(contrahent);
            break;
        default:
            throw new RuntimeException("not supported ball status: " + status);
        }
    }

    /**
     * Start session if players ready.
     */
    public boolean startSession() {
        if (initiator == null || contrahent == null) {
            return false;
        }

        sessionThread = new Thread(this);
        sessionThread.start();
        return true;
    }

    public void run() {
        while (sessionActive && !Thread.currentThread().isInterrupted()) {
            animateBall();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                // Restore interrupt status and exit gracefully
                Thread.currentThread().interrupt();
                break;
            }
        }
        this.sessionActive = false;
    }
}
