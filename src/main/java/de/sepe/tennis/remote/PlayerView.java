package de.sepe.tennis.remote;

import java.awt.*;
import java.awt.geom.*;

import de.sepe.tennis.remote.data.*;

/**
 * PlayerView.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 * @TODO movement msg to server needed
 */
public class PlayerView {

    /** size of one step */
    protected static final double STEP = 10;

    /** name */
    protected String name;

    /** position */
    protected Point2D position;

    /** color */
    protected Color color;

    /** action diameter */
    protected Rectangle2D diameter;

    /** size */
    protected double size;

    /** score */
    protected int score;

    /** controller */
    private Controller controller;

    /**
     * Constructor.
     * 
     * @param name the name
     */
    public PlayerView(String name) {
        this(name, Color.red);
    }

    /**
     * Constructor.
     * 
     * @param name the name
     * @param color the color
     */
    public PlayerView(String name, Color color) {
        this.name = name;
        this.color = color;
        this.size = 20;
        this.position = new Point2D.Double();
        this.diameter = new Rectangle();
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
        ++score;
    }

    /**
     * Sets the controller.
     * 
     * @param controller The controller to set
     */
    public void setController(Controller myController) {
        this.controller = myController;
    }

    /**
     * @return
     */
    public PlayerData getData() {
        return new PlayerData(name, position.getX(), position.getY());
    }

    /**
     * @param data
     */
    public void setData(PlayerData data) {
        this.name = data.name;
        this.position.setLocation(data.x, data.y);
    }
}
