package de.sepe.tennis.remote;

import java.awt.*;
import java.awt.geom.*;

import de.sepe.tennis.remote.data.*;

/**
 * BallView.
 * 
 * @author Sebastian Peters
 */
public class BallView {

    /** ball painting */
    protected Ellipse2D.Double delegate;

    /** color */
    protected Color color;

    public BallView() {
        this.delegate = new Ellipse2D.Double();
        this.color = Color.YELLOW;
    }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public BallView(double x, double y, double width, double height) {
        this.delegate = new Ellipse2D.Double(x, y, width, height);
        this.color = Color.yellow;
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

    @Override
    public String toString() {
        return "ball at (" + delegate.getCenterX() + ", " + delegate.getCenterY() + ")";
    }

    public synchronized BallData getData() {
        return new BallData(delegate.x, delegate.y, delegate.width, delegate.height);
    }

    public synchronized void setData(BallData data) {
        delegate.x = data.x;
        delegate.y = data.y;
        delegate.width = data.width;
        delegate.height = data.height;
    }
}
