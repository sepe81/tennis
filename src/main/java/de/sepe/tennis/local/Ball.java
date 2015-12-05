package de.sepe.tennis.local;

import java.awt.*;
import java.awt.geom.*;

/**
 * The ball to play.
 * 
 * @author Sebastian Peters
 */
public class Ball {

    /** ball painting */
    private Ellipse2D.Double delegate;

    /** color */
    private Color color;

    /** action diameter */
    private Rectangle2D diameter;

    /** x movement factor */
    private double dx;
    /** y movement factor */
    private double dy;

    /** random nr generator */
    private java.util.Random rand = new java.util.Random();

    /**
     * Constructor.
     */
    public Ball() {
        this(17.5, 17.5, 12, 12);
    }

    /**
     * Constructor.
     */
    public Ball(double x, double y, double width, double height) {
        this.delegate = new Ellipse2D.Double(x, y, width, height);
        this.color = Color.yellow;
        this.dx = 4;
        this.dy = 0;
    }

    /**
     * Draws the ball.
     * 
     * @param g gc
     */
    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(color);
        g.fill(delegate);
        g.setColor(oldColor);
    }

    /**
     * Hit by player.
     * 
     * @param player
     * @return player hit the ball
     */
    public boolean hit(Player player) {
        final Point2D p = player.getPosition();
        final double s = player.getSize();

        if (delegate.intersects(p.getX(), p.getY(), 0.001, s)) {
            dx = -dx; // change direction

            if (delegate.getCenterY() < p.getY() + s / 2) {
                dy = -rand.nextDouble() * Math.abs(dx);
                // dy =
                // -Math.abs(dx)
                // * ((s / 2 + p.getY() - delegate.getCenterY())
                // / (s / 2 + p.getY()));
            } else if (delegate.getCenterY() > p.getY() + s / 2) {
                dy = rand.nextDouble() * Math.abs(dx);
                // dy =
                // +Math.abs(dx)
                // * ((s / 2 + delegate.getCenterY() - p.getY())
                // / (s / 2 + p.getY()));
            } else {
                // hit the middle
                dy = 0;
            }

            return true;
        }
        return false;
    }

    /**
     * Move the ball.
     * 
     * @return status 0...in field 1...left out of bounds 2...right out of bounds
     */
    public int move() {
        double newX = delegate.getX() + dx;
        double newY = delegate.getY() + dy;

        if (newX <= diameter.getX()) {
            dx = -dx;
            return 1;
        } else if (newX + delegate.getWidth() >= diameter.getWidth()) {
            dx = -dx;
            return 2;
        }

        if (newY <= diameter.getY()) {
            dy = -dy;
            newY = 0;
        } else if (newY + delegate.getHeight() >= diameter.getHeight()) {
            dy = -dy;
            newY = diameter.getHeight() - delegate.getHeight();
        }

        delegate.setFrame(newX, newY, delegate.getWidth(), delegate.getHeight());

        return 0;
    }

    /**
     * Sets the diameter.
     * 
     * @param diameter The diameter to set
     */
    public void setDiameter(Rectangle2D diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "ball at (" + delegate.getCenterX() + ", " + delegate.getCenterY() + ")";
    }
}
