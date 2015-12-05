package de.sepe.tennis.local;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * the controller of a player saves the current movement and reacts on key actions.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public final class Controller implements KeyListener, Serializable {

    // direction flags
    private boolean up, down, left, right;

    // key for each direction
    private int eUp, eDown, eLeft, eRight;

    /**
     * constructor.
     * 
     * @param eUp up key
     * @param eDown down key
     * @param eLeft left key
     * @param eRight right key
     */
    public Controller(int eUp, int eDown, int eLeft, int eRight) {
        setEvents(eUp, eDown, eLeft, eRight);
        up = down = left = right = false;
    }

    /**
     * to which keys should i listen.
     * 
     * @param eUp up key
     * @param eDown down key
     * @param eLeft left key
     * @param eRight right key
     */
    public void setEvents(int eUp, int eDown, int eLeft, int eRight) {
        this.eUp = eUp;
        this.eDown = eDown;
        this.eLeft = eLeft;
        this.eRight = eRight;
    }

    /**
     * Returns the down.
     * 
     * @return boolean
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Returns the left.
     * 
     * @return boolean
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Returns the right.
     * 
     * @return boolean
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Returns the up.
     * 
     * @return boolean
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent e) {
        // not implemented
    }

    /**
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == eUp) {
            up = true;
            down = false;
        } else if (e.getKeyCode() == eDown) {
            up = false;
            down = true;
        } else if (e.getKeyCode() == eLeft) {
            left = true;
            right = false;
        } else if (e.getKeyCode() == eRight) {
            left = false;
            right = true;
        }
    }

    /**
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == eUp) {
            up = false;
        } else if (e.getKeyCode() == eDown) {
            down = false;
        } else if (e.getKeyCode() == eLeft) {
            left = false;
        } else if (e.getKeyCode() == eRight) {
            right = false;
        }
    }
}
