package de.sepe.tennis.remote.data;

import java.io.*;

/**
 * BallData.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public class BallData implements Serializable {

    private static final long serialVersionUID = 1L;

    public double x, y, width, height;

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public BallData(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
