package de.sepe.tennis.local;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * The court to play on.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public class Court extends JPanel implements Runnable {

    public enum Type {
        HARD, SAND, GRASS, BLACK
    }

    // 2 players on the court
    private Player player1, player2;

    /** ball to play */
    private Ball ball;

    /** game flag */
    private boolean gameStarted = false;

    /**
     * Constructor.
     */
    public Court() {
        initialize();
    }

    /**
     * initialize.
     */
    protected void initialize() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(400, 200));
        setSize(new Dimension(400, 200));

        // set ball and allowed positions
        ball = new Ball();
        ball.setDiameter(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        setAntiAliasing(g2d);

        drawNet(g2d);

        if (gameStarted) {
            player1.draw(g2d);
            player2.draw(g2d);
            ball.draw(g2d);
        }
    }

    /**
     * AntiAliasing fr den jeweiligen Grafikkontext setzen.
     * 
     * @param g2d der Grafikkontext
     */
    public void setAntiAliasing(Graphics2D g2d) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(renderHints);
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

    /*
     * @see de.sepe.tennis.local.CourtType#setCourtType(de.sepe.tennis.local.CourtType .Type)
     */
    public void setCourtType(Type type) {
        switch (type) {
        case HARD:
            setBackground(Color.red);
            break;
        case SAND:
            setBackground(Color.yellow);
            break;
        case GRASS:
            setBackground(Color.green);
            break;
        case BLACK:
            setBackground(Color.black);
            break;
        default:
            setBackground(Color.white);
            throw new RuntimeException("undefined type of court");
        }
    }

    /**
     * Move the ball & check for hits or out of bounds.
     */
    private void animateBall() {
        final int status = ball.move();
        switch (status) {
        case 1:
            player2.incScore();
            break;
        case 2:
            player1.incScore();
            break;
        case 0:
            ball.hit(player1);
            ball.hit(player2);
            break;
        default:
            throw new RuntimeException("not supported ball status: " + status);
        }
    }

    /**
     * Start the game if players ready.
     */
    public void startGame() {
        if (player1 != null && player2 != null) {
            final Thread t = new Thread(this);
            t.start();
        }
    }

    /**
     * Animate ball and players.
     */
    @Override
    public void run() {
        this.gameStarted = true;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        while (true) {
            animateBall();
            player1.animate();
            player2.animate();
            repaint();
            try {
                Thread.sleep(35);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Set player 1 and define its range.
     * 
     * @param player the player
     */
    public void setPlayer1(Player player) {
        player1 = player;
        player1.setPosition(new Point2D.Double(20, 20));
        player1.setDiameter(new Rectangle2D.Double(0, 0, getWidth() / 2, getHeight()));
    }

    /**
     * Set player 2 and define its range.
     * 
     * @param player the player
     */
    public void setPlayer2(Player player) {
        player2 = player;
        player2.setPosition(new Point2D.Double(getWidth() - 20, 20));
        player2.setDiameter(new Rectangle2D.Double(getWidth() / 2, 0, getWidth(), getHeight()));
    }
}
