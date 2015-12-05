package de.sepe.tennis.local;

import java.awt.*;
import java.awt.geom.*;
import java.beans.*;

/**
 * The player on the court.
 * 
 * @author Sebastian Peters
 */
public class Player {

    /** size of one step */
    private static final double STEP = 10;

    /** name */
    private String name;

    /** position */
    private Point2D position;

    /** color */
    private Color color;

    /** action diameter */
    private Rectangle2D diameter;

    /** size */
    private double size;

    /** score */
    private int score;

    /** PropertyChangeSupport */
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /** controller */
    private Controller controller;

    /**
     * constructor.
     * 
     * @param name the name
     */
    public Player(String name) {
        this(name, Color.red);
    }

    /**
     * constructor.
     * 
     * @param name the name
     * @param color the color
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.size = 20;
        this.position = new Point2D.Double();
        this.diameter = new Rectangle();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * animates the player
     */
    public void animate() {
        if (controller.isUp()) {
            moveUp();
        }
        if (controller.isDown()) {
            moveDown();
        }
        if (controller.isLeft()) {
            moveLeft();
        }
        if (controller.isRight()) {
            moveRight();
        }
    }

    /**
     * draws the player
     * 
     * @param g gc
     */
    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(color);

        // final int x = (int) position.getX();
        // final int y = (int) position.getY();

        // g.drawLine(x, y, x, (int) (y + size / 2));
        // g.drawLine(x, (int) (y + size / 3), (int) (x - size / 4), y);
        // g.drawLine(x, (int) (y + size / 3), (int) (x + size / 4), y);
        // g.drawLine(
        // x,
        // (int) (y + size / 2),
        // (int) (x - size / 3),
        // (int) (y + size));
        // g.drawLine(
        // x,
        // (int) (y + size / 2),
        // (int) (x + size / 3),
        // (int) (y + size));

        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Line2D player = new Line2D.Double(position, new Point2D.Double(position.getX(), position.getY() + size));
        g.draw(player);
        g.setColor(oldColor);

    }

    /**
     * move up
     */
    private void moveUp() {
        final double y = position.getY() - STEP;
        if (y >= diameter.getY()) {
            position.setLocation(position.getX(), y);
        }
    }

    /**
     * move down
     */
    private void moveDown() {
        final double y = position.getY() + STEP;
        if (y + size <= diameter.getHeight()) {
            position.setLocation(position.getX(), y);
        }
    }

    /**
     * move left
     */
    private void moveLeft() {
        final double x = position.getX() - STEP;
        if (x > diameter.getX()) {
            position.setLocation(x, position.getY());
        }
    }

    /**
     * move right
     */
    private void moveRight() {
        final double x = position.getX() + STEP;
        if (x < diameter.getWidth()) {
            position.setLocation(x, position.getY());
        }
    }

    @Override
    public String toString() {
        return name + " at " + position;
    }

    /**
     * Returns the name.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position.
     * 
     * @return Point2D
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Sets the name.
     * 
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the position.
     * 
     * @param position The position to set
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Returns the diameter.
     * 
     * @return Rectangle2D
     */
    public Rectangle2D getDiameter() {
        return diameter;
    }

    /**
     * Sets the diameter.
     * 
     * @param diameter The diameter to set
     */
    public void setDiameter(Rectangle2D diameter) {
        this.diameter = diameter;
    }

    /**
     * Returns the size.
     * 
     * @return double
     */
    public double getSize() {
        return size;
    }

    /**
     * Sets the size.
     * 
     * @param size The size to set
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Method getScore.
     */
    public int getScore() {
        return score;
    }

    /**
     * Incs the score.
     */
    public void incScore() {
        pcs.firePropertyChange("score", score, ++score);
    }

    /**
     * Sets the controller.
     * 
     * @param controller The controller to set
     */
    public void setController(Controller myController) {
        this.controller = myController;
    }
}
