package de.sepe.tennis.remote.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sepe.tennis.remote.BallView;
import de.sepe.tennis.remote.Server;
import de.sepe.tennis.remote.data.BallData;

/**
 * The court to play on.
 *
 * @author Sebastian Peters
 * @since 15.12.2003
 */
public class Court extends JPanel implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Court.class);

    private final String SESSION = "pingpong";

    private final Tennis game;

    private BallView ball;

    /** game flag */
    private boolean gameRuns = false;

    private Server server;

    /**
     * Constructor.
     */
    public Court(Tennis tennis) {
        this.game = tennis;
        initialize();
    }

    /**
     * Start the game if players ready.
     */
    public void startGame() {
        try {
            findServer();

            this.setBall(server.getBallForSession(SESSION));
            game.setPlayer(server.getInitiatorForSession(SESSION));
            game.setContrahent(server.getContrahentForSession(SESSION));
            server.startSession(SESSION);

            gameRuns = true;
            final Thread t = new Thread(this);
            t.start();
        } catch (final Exception e) {
            JOptionPane.showMessageDialog(this, "Keine Verbindung zum Server!");
            log.error("Can't find server or remote objects.", e);
        }
    }

    /**
     * @param data
     */
    private void setBall(BallData data) {
        ball = new BallView();
        ball.setData(data);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        setAntiAliasing(g2d);

        drawNet(g2d);

        if (gameRuns) {
            try {

                game.getPlayer().setData(server.getInitiatorForSession(SESSION));
                game.getPlayer().draw(g2d);
                game.getContrahent().setData(server.getContrahentForSession(SESSION));
                game.getContrahent().draw(g2d);

                ball.setData(server.getBallForSession(SESSION));
                ball.draw(g2d);
            } catch (final RemoteException e) {
                // TODO exception handling
                e.printStackTrace();
            }
        }
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        log.info("animation started");
        while (gameRuns) {
            game.getPlayer().animate();
            repaint();
            try {
                Thread.sleep(20);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Initialize.
     */
    private void initialize() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(400, 200));
        setSize(new Dimension(400, 200));
    }

    /**
     * Draw the net.
     *
     * @param g Graphics2D
     */
    private void drawNet(Graphics2D g) {
        final Color oldColor = g.getColor();
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));

        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        g.setStroke(new BasicStroke());
        g.setColor(oldColor);
    }

    /**
     * AntiAliasing für den jeweiligen Grafikkontext setzen.
     *
     * @param g2d der Grafikkontext
     */
    private void setAntiAliasing(Graphics2D g2d) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(renderHints);
    }

    /**
     * Find a server.
     *
     * @throws Exception if no server could by found
     */
    private void findServer() throws Exception {
        this.server = (Server) Naming.lookup("//localhost/tennisServer");
        server.createSession(SESSION);
    }
}
